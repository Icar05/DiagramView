package dv.dvproject

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.Icar05.diagramview.DiagramView

class MainActivity : AppCompatActivity() {



    private var topRight: DiagramView? = null

    private var topLeft: DiagramView? = null

    private var bottomRight: DiagramView? = null

    private var bottomLeft: DiagramView? = null

    private val bigContentHelper = BigContentHelper()

    private var bottomLeftDiagramEngine: SimpleDiagramEngine? = null

    private var topLeftDiagramEngine: SimpleDiagramEngine? = null

    private var bottomRightDiagramEngine: SimpleDiagramEngine? = null

    private var topRightDiagramEngine: SimpleDiagramEngine? = null


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        topRight = findViewById(R.id.dvTopRight)
        topLeft = findViewById(R.id.dvTopLeft)
        bottomRight = findViewById(R.id.bottomRight)
        bottomLeft = findViewById(R.id.bottomLeft)


        //first set up
        topRight?.setContent(ContentHelper())
        topLeft?.setContent(ContentHelper())
        bottomRight?.setContent(ContentHelper())
        bottomLeft?.setContent(bigContentHelper.source)


        bottomLeftDiagramEngine = SimpleDiagramEngine(bottomLeft, 500)
        topRightDiagramEngine = SimpleDiagramEngine(topRight)
        topLeftDiagramEngine = SimpleDiagramEngine(topLeft)
        bottomRightDiagramEngine = SimpleDiagramEngine(bottomRight)

        topRight?.setOnTouchListener { _: View, _: MotionEvent -> refreshTopRight() }
        topLeft?.setOnTouchListener { _: View, _: MotionEvent -> emitDataToTopLeft() }
        bottomLeft?.setOnTouchListener { _: View, _: MotionEvent -> startBtlAnim() }
        bottomRight?.setOnTouchListener { _: View, _: MotionEvent -> startBTRAnim() }


    }

    private fun emitDataToTopLeft(): Boolean {
         topLeftDiagramEngine?.start()
         topLeftDiagramEngine?.addValue(bigContentHelper.randomData)
         return false
    }


    //animate bottom right
    private fun startBTRAnim(): Boolean {
        bigContentHelper.setDelegate(bottomRightDiagramEngine)
        bottomRightDiagramEngine?.start()

        bigContentHelper.emitData(100)
        return false
    }



      //anim bttom left
    private fun startBtlAnim(): Boolean {
        bigContentHelper.setDelegate(bottomLeftDiagramEngine)
        bottomLeftDiagramEngine?.start()


        bigContentHelper.emitData(50)
        return false
    }


    private fun refreshTopRight(): Boolean {
        showDialog()
        return false
    }


    private fun startAlertAnimation(engine: SimpleDiagramEngine): Boolean{
        bigContentHelper.setDelegate(engine)
        engine.start()

        bigContentHelper.emitData(50)
        return false
    }


    @SuppressLint("InflateParams", "ClickableViewAccessibility")
    private fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(this)

        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_layout, null)
        dialogBuilder.setView(dialogView)

        val diagram = dialogView.findViewById(R.id.dvTopLeft) as DiagramView
        val diagramEngine = SimpleDiagramEngine(diagram)
        diagram.setContent(ContentHelper())
        diagram.setOnTouchListener { _: View, _: MotionEvent ->
            startAlertAnimation(diagramEngine)
        }

        dialogBuilder.setPositiveButton("hide dialog", null)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }


    override fun onDestroy() {
        topRightDiagramEngine?.pause()
        topRightDiagramEngine?.stop()
        bottomRightDiagramEngine?.stop()
        topLeftDiagramEngine?.stop()
        bottomLeftDiagramEngine?.stop()
        super.onDestroy()
    }
}


