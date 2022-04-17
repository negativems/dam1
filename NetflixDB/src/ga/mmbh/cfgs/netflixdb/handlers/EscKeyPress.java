package ga.mmbh.cfgs.netflixdb.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class EscKeyPress implements KeyListener {

	private final JFrame frame;
	
	public EscKeyPress(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            frame.dispose();
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
