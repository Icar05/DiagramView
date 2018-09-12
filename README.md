# DiagramView
This view will help you, if you need to draw fine graphic. It pretty convenient and easy in
usage

![alt text](https://github.com/Icar05/DiagramView/blob/master/giphy.gif)

If you want to use this view, you have to create class, which will inherit DiagramDataHolder,
or you can simple use SimpleHolder(). Each step in graphic - is integer, which  shouldn't be < 1.

Simple usage looks like next:
       
![alt text](https://github.com/Icar05/DiagramView/blob/master/simple_usage_view.png)

Usage animation: 

![alt text](https://github.com/Icar05/DiagramView/blob/master/usage_anim.png)

# Download
implementation 'com.github.Icar05:diagramview:0.1.0' <br>

[ ![Download](https://api.bintray.com/packages/icar05/diagramview/DiagramView/images/download.svg) ](https://bintray.com/icar05/diagramview/DiagramView/_latestVersion)


# Attributes

  • mTopColor - color that will start draw gradient from top <br><br>
  • mBottomColor - color that will start draw gradient from bottom <br><br>
  • mHorizontalLinesColor - color of horizontal lines, which show step in graphic <br><br>
  
  
# Classes

  • DiagramView - View for drawing graphics <br><br>
  • DiagramDataHolder - Abstract parent class of holder, which store and works with content <br><br>
  • DiagramException - Custom exception, cases: value < 1, step < 1, content.size() < 1, etc. <br><br>
  • SimpleHolder - Simple child of DiagramDataHolder, that has list of models in constructor <br><br>
  • DiagramEngine - in version > 0.1.0 will help create animation for DiagramView <br><br>

# License

http://www.apache.org/licenses/LICENSE-2.0
