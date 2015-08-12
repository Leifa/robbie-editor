package de.leifaktor.robbie.editor.model.gfx;

import java.util.List;

public class Animation {
    
    /**
     * A list of frames.
     */
    
    private List<TileGraphic> frames;
    
    /**
     * The duration of one frame.
     */
    
    private double frequency;
    
    /**
     * The looping behaviour of the animaion.
     */
    
    private LoopStyle loopStyle;
    
    /**
     * Creates an Animation with the specified parameters.
     * @param frames The list of frames.
     * @param frequency The frequency.
     * @param loopStyle The loopStyle.
     */
    
    public Animation(List<TileGraphic> frames, double frequency, LoopStyle loopStyle) {
        this.frames = frames;
        this.frequency = frequency;
        this.loopStyle = loopStyle;
    }
    
    /**
     * The different kinds of LoopStyles
     */
    
    public enum LoopStyle {
        
        /**
         * After reaching the last frame the animation should start over from the first frame.
         */
        
        LOOPING,
        
        /**
         * The animation should play forwards, then backwards, then repeat.
         */
        
        PINGPONG,
        
        /**
         * The animation plays once, then stays in the last frame.
         */
        
        ONCE;
    }

}
