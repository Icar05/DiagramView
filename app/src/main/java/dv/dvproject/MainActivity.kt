package dv.dvproject

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.Icar05.diagramview.DiagramAnimator
import com.Icar05.diagramview.DiagramModel
import com.Icar05.diagramview.DiagramView
import com.Icar05.diagramview.SimpleHolder

class MainActivity : AppCompatActivity() {




    private var topRight: DiagramView? = null

    private var topLeft: DiagramView? = null

    private var bottomRight: DiagramView? = null

    private var bottomLeft: DiagramView? = null

    private val bigContentHelper = BigContentHelper()




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


        topRight?.setOnTouchListener { _: View, _: MotionEvent -> refreshTopRight() }
        topLeft?.setOnTouchListener { v: View, _: MotionEvent -> refreshTopLeft(v as DiagramView) }
        bottomLeft?.setOnTouchListener { _: View, _: MotionEvent -> startAnimation(bottomLeft!!) }
        bottomRight?.setOnTouchListener { _: View, _: MotionEvent -> startAnimation(bottomRight!!) }
    }


    private fun startAnimation(bottomRight: DiagramView): Boolean {
        val diagramAnimator = DiagramAnimator(bottomRight, 5000)
        diagramAnimator.animate(bigContentHelper.content)
        return false
    }



    private fun refreshTopLeft(view: DiagramView?): Boolean {

        val tempDiagramm: MutableList<DiagramModel> = mutableListOf()

        for (i in 10 downTo 1){
            tempDiagramm.add(DiagramModel(i*5, i))
        }

        view?.setContent(SimpleHolder(tempDiagramm))
        return true
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
            startAnimation(diagram)
        }

        dialogBuilder.setPositiveButton("hide dialog", null)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}


