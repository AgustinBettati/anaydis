package anaydis.sort.practice.ui;

import anaydis.animation.sort.gui.Main;
import anaydis.sort.SorterProviderImpl;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
class MainGUI {

    public static void main(String[] args) {

        Main.animate(new SorterProviderImpl());
    }
}
