import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.flyw1ng.ultragame.anim.AnimationProvider;
import com.flyw1ng.ultragame.anim.TimerAnimation;

class TimerAnimationTest {

    private TimerAnimation animationWithDelay;
    private TimerAnimation animationWithoutDelay;

    @BeforeEach
    void setUp() {
        animationWithDelay = new TimerAnimation(3, true);
        animationWithoutDelay = new TimerAnimation(3, false);
    }

    // --- Initial state ---
    @Test
    void testInitialState() {
        assertFalse(animationWithDelay.isAnimating());
        assertFalse(animationWithDelay.hasJustFinished());
        assertEquals(0, animationWithDelay.getCurrentFrame());
    }

    // --- start() ---
    @Test
    void testWithoutDelayStart() {
        animationWithoutDelay.start();
        assertTrue(animationWithoutDelay.isAnimating());
        assertFalse(animationWithoutDelay.hasJustFinished());
        assertEquals(0, animationWithoutDelay.getCurrentFrame());
    }
    @Test
    void testDelayStart(){
        animationWithDelay.start();
        assertTrue(animationWithDelay.isAnimating());
        assertFalse(animationWithDelay.hasJustFinished());
        assertEquals(0, animationWithDelay.getCurrentFrame());
    }

    @Test
    void testAnimWithoutDelay(){
        testWithoutDelayStart();
        for (int i = 0; i < 3; i++){
            assertEquals(i, animationWithoutDelay.getCurrentFrame());
            animationWithoutDelay.update();
            if (i < 2) {
                assertTrue(animationWithoutDelay.isAnimating());
                assertFalse(animationWithoutDelay.hasJustFinished());
            }

        }
        assertFalse(animationWithoutDelay.isAnimating());
        assertTrue(animationWithoutDelay.hasJustFinished());
    }
    @Test
    void testAnimWithDelay(){
        testDelayStart();
        for (int i = 0; i < 3; i++){
            assertEquals(i, animationWithDelay.getCurrentFrame());
            animationWithDelay.update();
            if (i < 2){
                assertTrue(animationWithDelay.isAnimating());
                assertFalse(animationWithDelay.hasJustFinished());
            }
            if (i == 2){
                assertEquals(AnimationProvider.ANIM.DELAY, animationWithDelay.getStatus());
            }
        }
        for (int j = 0; j < 3; j++){
            assertEquals(j, animationWithDelay.getDelayTime());
            animationWithDelay.update();
            if (j < 2){
                assertEquals(AnimationProvider.ANIM.DELAY, animationWithDelay.getStatus());
                assertFalse(animationWithDelay.hasJustFinished());
            }
        }

        assertTrue(animationWithDelay.hasJustFinished());
    }

}
