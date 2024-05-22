package up.tac.cmsc12.mp.ui;

import java.awt.GridBagConstraints;

public class GBCUtils {
    GridBagConstraints gbc;

    public GBCUtils(GridBagConstraints gbc) {
        this.gbc = gbc;
    }

    public void setGBC(int gridx, int gridy){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
    }

    public void setGBC(int gridx, int gridy, double weightx, double weighty){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }

    public void setGBC(int gridx, int gridy, int anchor, double weightx, double weighty, int fill){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
    }

    public void setGBC(double weightx, double weighty){
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }
}
