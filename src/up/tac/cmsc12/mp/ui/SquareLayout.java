package up.tac.cmsc12.mp.ui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;

public class SquareLayout extends GridLayout{
    public SquareLayout(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            int nrows = getRows();
            int ncols = getColumns();
            boolean ltr = parent.getComponentOrientation().isLeftToRight();
    
            if (ncomponents == 0) {
                return;
            }
            if (nrows > 0) {
                ncols = (ncomponents + nrows - 1) / nrows;
            } else {
                nrows = (ncomponents + ncols - 1) / ncols;
            }
            // magic happens here
            // in case there are gaps in between components
            // or insets in the grid panel itself
            int totalGapsWidth = (ncols - 1) * getHgap();
            int widthWOInsets = parent.getWidth() - (insets.left + insets.right);
            int totalGapsHeight = (nrows - 1) * getVgap();
            int heightWOInsets = parent.getHeight() - (insets.top + insets.bottom);
            // calculate the width needed
            int widthOnComponent = (widthWOInsets - totalGapsWidth) / ncols;
            int heightOnComponent = (heightWOInsets - totalGapsHeight) / nrows;
            // get the smallest of the two and use it as the size. it becomes square now yay.
            int size = Math.min(widthOnComponent, heightOnComponent);
            widthOnComponent = size;
            heightOnComponent = size;
            
            int extraWidthAvailable = (widthWOInsets - (widthOnComponent * ncols + totalGapsWidth)) / 2;
            int extraHeightAvailable = (heightWOInsets - (heightOnComponent * nrows + totalGapsHeight)) / 2;
            
            if (ltr) {
                for (int c = 0, x = insets.left + extraWidthAvailable; c < ncols ; c++, x += widthOnComponent + getHgap()) {
                    for (int r = 0, y = insets.top + extraHeightAvailable; r < nrows ; r++, y += heightOnComponent + getVgap()) {
                        int i = r * ncols + c;
                        if (i < ncomponents) {
                            parent.getComponent(i).setBounds(x, y, widthOnComponent, heightOnComponent);
                        }
                    }
                }
            } else {
                for (int c = 0, x = (parent.getWidth() - insets.right - widthOnComponent) - extraWidthAvailable; c < ncols ; c++, x -= widthOnComponent + getHgap()) {
                    for (int r = 0, y = insets.top + extraHeightAvailable; r < nrows ; r++, y += heightOnComponent + getVgap()) {
                        int i = r * ncols + c;
                        if (i < ncomponents) {
                            parent.getComponent(i).setBounds(x, y, widthOnComponent, heightOnComponent);
                        }
                    }
                }
            }
          }
    }
}
