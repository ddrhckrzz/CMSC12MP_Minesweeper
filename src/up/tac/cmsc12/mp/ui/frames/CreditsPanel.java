package up.tac.cmsc12.mp.ui.frames;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.GBCUtils;
import up.tac.cmsc12.mp.ui.buttons.CustomButton;

public class CreditsPanel extends JPanel {
    private final String FB_LINK = "https://www.facebook.com/";
    CustomButton avila, lagramada, lagarto, martillo;

    public CreditsPanel() {
        init_layout();
        add_buttons();
        addFBLinks();
    }

    private void init_layout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0, 0));
    }

    private void add_buttons() {
        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setLayout(new GridBagLayout());
        buttonWrapper.setBackground(new Color(0, 0, 0, 0));
        GridBagConstraints c = new GridBagConstraints();
        GBCUtils gbcu = new GBCUtils(c);

        CustomButton credits = new CustomButton("CREDITS");
        avila = new CustomButton("AVILA, ALEX IAN");
        lagramada = new CustomButton("LAGRAMADA, JUDE ANTHONY");
        lagarto = new CustomButton("LAGARTO, NYK DAVID");
        martillo = new CustomButton("MARTILLO, ANDREI ERNEST");

        gbcu.setInsets(4, 0, 4, 0);
        gbcu.setAnchorAndFill(GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        int gridx = 0, gridy = 0;
        gbcu.setGBC(gridx,gridy);
        buttonWrapper.add(credits, c);
        gridy++;
        gbcu.setGBC(gridx, gridy);
        buttonWrapper.add(avila, c);
        gridy++;
        gbcu.setGBC(gridx, gridy);
        buttonWrapper.add(lagramada, c);
        gridy++;
        gbcu.setGBC(gridx, gridy);
        buttonWrapper.add(lagarto, c);
        gridy++;
        gbcu.setGBC(gridx, gridy);
        buttonWrapper.add(martillo, c);

        add(buttonWrapper);
    }

    private void addFBLinks() {
        Desktop desktop = Desktop.getDesktop();
        avila.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    URI uri = new URI(FB_LINK + "alexian.avila");
                    desktop.browse(uri.resolve(uri));
                } catch (URISyntaxException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        lagramada.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    URI uri = new URI(FB_LINK + "actuallyjudeanthony0327");
                    desktop.browse(uri.resolve(uri));
                } catch (URISyntaxException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        lagarto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    URI uri = new URI(FB_LINK + "profile.php?id=100009643459604");
                    desktop.browse(uri.resolve(uri));
                } catch (URISyntaxException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        martillo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    URI uri = new URI(FB_LINK + "ddrhckrzz");
                    desktop.browse(uri.resolve(uri));
                } catch (URISyntaxException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
