	//Объект перемещается нажатием клавиш мышки и колесиком меняет размер

package Archive;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.eclipse.jdt.internal.compiler.codegen.VerificationTypeInfo;

public class MouseMoveScale extends JPanel {
  private Rectangle2D.Float myRect = new Rectangle2D.Float(50, 50, 50, 50);//Constructs and initializes a Rectangle2D from the specified float coordinates.

  MovingAdapter ma = new MovingAdapter();

  public MouseMoveScale() {
    addMouseMotionListener(ma);
    addMouseListener(ma);
    addMouseWheelListener(new ScaleHandler());
  }

  public void paint(Graphics g) {
    super.paint(g); // paint parent's background

    Graphics2D g2d = (Graphics2D) g; // downcast the Graphics object back to Graphics2D
    //   Создается вторая ссылка, g2d и ей присваивается ссылка на тот же объект, 
//    но на этот раз она относится к ней как к типу Graphics2D объекта, 
//    который является более конкретным типом. Существует только один объект и две вещи, 
//    которые относятся к нему. Тем не менее, две вещи видят объект по-разному. 
//    g имеет доступ только к подмножеству функций, общих для всех Graphics объектов. 
//    g2d имеет доступ к этому в дополнение к функциональности Graphics2D объектов.

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//включить сглаживание всех отрисовываемых в дальнейшем фигур
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//включить сглаживание текста

    g2d.setColor(new Color(0, 0, 200));//RGB
    g2d.fill(myRect);//fill
  }

  class MovingAdapter extends MouseAdapter {//используем соответствующий MouseAdapter AWT-класс, который реализует MouseMotionListener интерфейс, 
											//для создания MouseMotionEvent и переопределения методов для определенных событий

    private int x;

    private int y;

    
    //@Override  - переопределяем метод базового класса - в данном случае он и так будет переопределен и эта Аннотация служит лишь для контроля успешности действия при сборке проекта.
    public void mousePressed(MouseEvent e) {
      x = e.getX();
      y = e.getY();
    }

    public void mouseDragged(MouseEvent e) {

      int dx = e.getX() - x;
      int dy = e.getY() - y;

      if (myRect.getBounds2D().contains(x, y)) {
        myRect.x += dx;
        myRect.y += dy;
        repaint();
      }
      x += dx;
      y += dy;
    }
  }

  class ScaleHandler implements MouseWheelListener {
    public void mouseWheelMoved(MouseWheelEvent e) {//Invoked when the mouse wheel is rotated

      int x = e.getX();
      int y = e.getY();

      if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

        if (myRect.getBounds2D().contains(x, y)) {
          float amount = e.getWheelRotation() * 5f;
          myRect.width += amount;
          myRect.height += amount;
          repaint();
        }
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Test Code Moving and Scaling");
    MouseMoveScale m = new MouseMoveScale();
    m.setDoubleBuffered(true);
    
    frame.add(m);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	m.setDoubleBuffered(true);
	
    frame.setSize(500, 500);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
