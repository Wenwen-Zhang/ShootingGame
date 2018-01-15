package main.se450.singletons;

import java.util.HashMap;

import main.se450.constants.Constants;
import main.se450.interfaces.ISound;
import main.se450.sound.BigExplosion;
import main.se450.sound.Fire;
import main.se450.sound.ForwardThrust;
import main.se450.sound.MediumExplosion;
import main.se450.sound.ReverseThrust;
import main.se450.sound.ShipExplosion;
import main.se450.sound.SmallExplosion;

/**
 * The SoundManager class manages the sound effects that would be played when certain event occurs.
 */
public class SoundManager
{
	private static SoundManager soundManager = null;
	
	private HashMap<String,ISound> sounds = null;
	
	static
	{
		soundManager = new SoundManager();
	}
	
    private SoundManager()
    {
    	sounds = new HashMap<String,ISound>();
    	
    	sounds.put(Constants.FIRE,                   new Fire());
    	sounds.put(Constants.FORWARD_THRUST_PRESSED, new ForwardThrust());
    	sounds.put(Constants.REVERSE_THRUST_PRESSED, new ReverseThrust());
    	sounds.put(Constants.SHIPEXPLOSION,          new ShipExplosion());
    	sounds.put(Constants.BIGEXPLOSION,           new BigExplosion());
    	sounds.put(Constants.MEDIUMEXPLOSION,        new MediumExplosion());
    	sounds.put(Constants.SMALLEXPLOSION,         new SmallExplosion());
    	
    }
    
	public final static SoundManager getSoundManager() 
	{
		return soundManager;
	}
	
	public void playFire()
	{
		sounds.get(Constants.FIRE).play();
	}
	
	public void playForwardThrust()
	{
		sounds.get(Constants.FORWARD_THRUST_PRESSED).play();
	}
	
	public void playReverseThrust()
	{
		sounds.get(Constants.REVERSE_THRUST_PRESSED).play();
	}
	
	public void playShipExplosion()
	{
		sounds.get(Constants.SHIPEXPLOSION).play();
	}
	
	public void playBigExplosion()
	{
		sounds.get(Constants.BIGEXPLOSION).play();
	}
	
	public void playMediumExplosion()
	{
		sounds.get(Constants.MEDIUMEXPLOSION).play();
	}
	
	public void playSmallExplosion()
	{
		sounds.get(Constants.SMALLEXPLOSION).play();
	}
			
}
      