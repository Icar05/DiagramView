package dv.dvproject

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.Icar05.diagramview.*

class MainActivity : AppCompatActivity(), BigContentHelper.bigContentHelper {



    private var topRight: DiagramView? = null

    private var topLeft: DiagramView? = null

    private var bottomRight: DiagramView? = null

    private var bottomLeft: DiagramView? = null

    private val bigContentHelper = BigContentHelper()

    private var diagramEngine: DiagramEngine? = null

    private var topLeftDiagramEngine: DiagramEngine? = null

    private var bottomRightDiagramEngine: DiagramEngine? = null



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        topRight = findViewById(R.id.dvTopRight)
        topLeft = findViewById(R.id.dvTopLeft)
        bottomRight = findViewById(R.id.bottomRight)
        bottomLeft = findViewById(R.id.bottomLeft)


        //first set up
        topRight?.setContent(ContentHelper(3, 18))
        topLeft?.setContent(ContentHelper(2, 25))
        bottomRight?.setContent(ContentHelper(3, 20))
        bottomLeft?.setContent(bigContentHelper.source)


        prepareTopLeftAnimation()
        diagramEngine = DiagramEngine(bottomRight)
        bottomRightDiagramEngine = DiagramEngine(bottomLeft)

        topRight?.setOnTouchListener { _: View, _: MotionEvent -> refreshTopRight() }
        topLeft?.setOnTouchListener { _: View, _: MotionEvent -> emitDataToTopLeft() }
        bottomLeft?.setOnTouchListener { _: View, _: MotionEvent -> startAnimation() }
        bottomRight?.setOnTouchListener { _: View, _: MotionEvent -> startNewAnimation() }
    }

    private fun emitDataToTopLeft(): Boolean {
         topLeftDiagramEngine?.addValue(bigContentHelper.randomData)
         return false
    }

    private fun prepareTopLeftAnimation() {
        topLeftDiagramEngine = DiagramEngine(topLeft)
        topLeftDiagramEngine?.start()
    }


    override fun getNewContent(model: DiagramModel?) {
        diagramEngine?.addValue(model)
    }

    private fun startNewAnimation(): Boolean {
        diagramEngine?.start()

        bigContentHelper.setDelegate(this)
        bigContentHelper.emitData(100)
        return false
    }




    private fun startAnimation(): Boolean {
        bottomRightDiagramEngine?.start()

        bigContentHelper.setDelegate(this)
        bigContentHelper.emitData(50)
        return false
    }




    private fun refreshTopRight(): Boolean {
        showDialog()
        return false
    }


    @SuppressLint("InflateParams", "ClickableViewAccessibility")
    private fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(this)

        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_layout, null)
        dialogBuilder.setView(dialogView)

        val diagram = dialogView.findViewById(R.id.dvTopLeft) as DiagramView
        diagram.setContent(ContentHelper(2, 25))
        diagram.setOnTouchListener { _: View, _: MotionEvent ->
            startAnimation()
        }

        dialogBuilder.setPositiveButton("hide dialog", null)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }


    override fun onDestroy() {
        topLeftDiagramEngine?.stop()
        diagramEngine?.stop()
        super.onDestroy()
    }
}


