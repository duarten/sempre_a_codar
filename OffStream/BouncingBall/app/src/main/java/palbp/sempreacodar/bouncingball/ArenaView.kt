package palbp.sempreacodar.bouncingball

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceView
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread

/**
 * View used to display the game arena. This class instances are confined to the [Engine]'s thread,
 * that is, no other thread can access the view.
 */
class ArenaView @MainThread constructor(ctx: Context, attrs: AttributeSet?) : SurfaceView(ctx, attrs) {

    init { setZOrderOnTop(true) }

    /**
     * The brush to be used tp paint
     */
    private val brush: Paint = Paint(). apply {
        this.color = Color.RED
        this.style = Paint.Style.FILL_AND_STROKE
    }

    /**
     * The bouncing ball, if one exists
     */
    var ball: BouncingBall? = null
        @WorkerThread set
        @WorkerThread get

    /**
     * Called whenever the arena is to be painted.
     *
     * @param   canvas  The canvas where the painting is to take place
     */
    @WorkerThread
    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
        val theBall = ball ?: return
        canvas.drawCircle(theBall.position.x, theBall.position.y, theBall.radius, brush)
    }
}
