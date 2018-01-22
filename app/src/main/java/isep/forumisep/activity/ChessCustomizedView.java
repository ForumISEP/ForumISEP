package isep.forumisep.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import isep.forumisep.R;

import static java.security.AccessController.getContext;

/**
 * Created by linfengwang on 22/01/2018.
 */

public class ChessCustomizedView extends AppCompatActivity {
    private Paint mPaintOne=null;//couleur one
    private Paint mPaintTwo=null;//couleur two
/*
    public ChessCustomizedView(Context context){
        super(context);
        init(-1,-1);
    }

    public ChessCustomizedView(Context context,int one, int two){
        super(context);
        init(one,two);
    }

    private void init(int one,int two){
        mPaintTwo = new Paint(Paint.ANTI_ALIAS_FLAG);
        if(one==-1){
            mPaintTwo.setColor(Color.LTGRAY);
        }else{
            mPaintTwo.setColor(one);
        }

        mPaintOne=new Paint(Paint.ANTI_ALIAS_FLAG);
        if(two==-1){
            mPaintTwo.setColor(Color.WHITE);
        }else{
            mPaintOne.setColor(two);
        }
    }
 */
    //attrs est le paramètre qui contient les attributs de notre objet en XML

    public ChessCustomizedView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init(attrs);
    }

    public ChessCustomizedView(Context context,AttributeSet attrs){
        super(context,attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray attr=getContext().obtainSytleAttributes(attrs, R.styleable.ChessBoardView);
        int temOne=attr.getColor(R.styleable.ChessBoardView_colorOne,-1);
        int temTwo=attr.getColor(R.styleable.ChessBoardView_colorTwo,-1);
        init(temOne,temTwo);
    }

    //calcule la bonne mesure sur un axe uniquement
    private int singleMeasure(int spec,int screenDim){
        int mode= View.MeasureSpec.getMode(spec);
        int size= View.MeasureSpec.getSize(spec);

        //if the layout doesn't git the precise dimension, take half of the screen;
        if(mode== View.MeasureSpec.UNSPECIFIED)
            return screenDim/2;
        else
            return size;
    }

    @Override
    protected void onMeasure (int widthMeasureSpec,int heightMeasureSpec){
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();

        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        int retourWidth=singleMeasure(widthMeasureSpec, screenWidth);
        int retourHeight=singleMeasure(heightMeasureSpec,screenHeight);

        //an square, so one size is ok
        int retour=Math.min(retourHeight,retourWidth);

        setMeasureDimension(retour,retour);
    }

    @Override
    protected void onDraw(Canvas canvas){
        int width=getWidth();
        int height=getHeight();

        int step =0, min=0;
        min=Math.min(width,height);

        //we want 8*8 square
        step=min/8;
        boolean flag=true;
        for(int i=0;i<min; i+=step){
            for(int j=0;j<min;j+=step){
                if(flag){
                    canvas.drawRect(i,j,i+step,j+step,mPaintTwo);
                }else{
                    canvas.drawRect(i,j,i+step,j+step,mPaintOne);
                }
                flag=!flag;
            }
            flag=!flag;
        }
    }
}
