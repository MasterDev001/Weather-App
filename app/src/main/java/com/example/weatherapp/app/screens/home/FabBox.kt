package com.example.weatherapp.app.screens.home

import android.annotation.SuppressLint
import android.graphics.Point
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.example.weatherapp.app.screens.fabBoxSize_100
import com.example.weatherapp.ui.theme.backgroundColor


@SuppressLint("SuspiciousIndentation")
@Composable
fun FabBox() {

    val mFirstCurveStartPoint = Point()
    val mFirstCurveEndPoint = Point()
    val mFirstCurveControlPoint1 = Point()
    val mFirstCurveControlPoint2 = Point()

    //the coordinates of the second curve

    //the coordinates of the second curve
    var mSecondCurveStartPoint = Point()
    val mSecondCurveEndPoint = Point()
    val mSecondCurveControlPoint1 = Point()
    val mSecondCurveControlPoint2 = Point()
    var mNavigationBarWidth: Int = 0
    var mNavigationBarHeight =0

        Canvas(
            modifier = Modifier
                .size(fabBoxSize_100)

        ) {
            val width = size.width
            val height = size.height
            val CURVE_CIRCLE_RADIUS = (size.width.toInt()+20)/2;

            mNavigationBarWidth = width.toInt();
            mNavigationBarHeight = height.toInt();
            // the coordinates (x,y) of the start point before curve
            mFirstCurveStartPoint.set(
                (mNavigationBarWidth / 2) - (CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3),
                0
            );
            // the coordinates (x,y) of the end point after curve
            mFirstCurveEndPoint.set(
                mNavigationBarWidth / 2,
                CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4)
            );
            // same thing for the second curve
            mSecondCurveStartPoint = mFirstCurveEndPoint;
            mSecondCurveEndPoint.set(
                (mNavigationBarWidth / 2) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3),
                0
            );

            // the coordinates (x,y)  of the 1st control point on a cubic curve
            mFirstCurveControlPoint1.set(
                mFirstCurveStartPoint.x + CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4),
                mFirstCurveStartPoint.y
            );
            // the coordinates (x,y)  of the 2nd control point on a cubic curve
            mFirstCurveControlPoint2.set(
                mFirstCurveEndPoint.x - (CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS,
                mFirstCurveEndPoint.y
            );

            mSecondCurveControlPoint1.set(
                mSecondCurveStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS,
                mSecondCurveStartPoint.y
            );
            mSecondCurveControlPoint2.set(
                mSecondCurveEndPoint.x - (CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4)),
                mSecondCurveEndPoint.y
            );


            val path = Path().apply {
                reset();
                moveTo(0.toFloat(), 0.toFloat());
                lineTo(mFirstCurveStartPoint.x.toFloat(), mFirstCurveStartPoint.y.toFloat());

                cubicTo(
                    mFirstCurveControlPoint1.x.toFloat(), mFirstCurveControlPoint1.y.toFloat(),
                    mFirstCurveControlPoint2.x.toFloat(), mFirstCurveControlPoint2.y.toFloat(),
                    mFirstCurveEndPoint.x.toFloat(), mFirstCurveEndPoint.y.toFloat()
                );

                cubicTo(
                    mSecondCurveControlPoint1.x.toFloat(), mSecondCurveControlPoint1.y.toFloat(),
                    mSecondCurveControlPoint2.x.toFloat(), mSecondCurveControlPoint2.y.toFloat(),
                    mSecondCurveEndPoint.x.toFloat(), mSecondCurveEndPoint.y.toFloat()
                );

//            lineTo(mNavigationBarWidth.toFloat(), 0.toFloat());
//            lineTo(mNavigationBarWidth.toFloat(), mNavigationBarHeight.toFloat());
//            lineTo(0.toFloat(), mNavigationBarHeight.toFloat());
                close();
            }
            drawPath(path = path, color = backgroundColor)
        }

}
