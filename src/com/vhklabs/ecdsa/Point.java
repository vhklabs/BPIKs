package com.vhklabs.ecdsa;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 坐标点
 */
public class Point {
    static BigInteger INFINITY = new BigInteger("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
    static Point POINT_AT_INFINITY = new Point(new BigInteger("0"),INFINITY);
    private BigInteger x;
    private BigInteger y;
    public Point(BigInteger x,BigInteger y){
        this.x = x;
        this.y = y;
    }
    public void setX(BigInteger x) {
        this.x = x;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Point point = (Point) obj;
        boolean flag = false;
        if(this.getY().equals(point.getY()) && this.getY().equals(INFINITY)){flag = true;}
        else if(this.getY().equals(point.getY()) && this.getX().equals(point.getX())){flag=true;}
        return flag;
    }



    @Override
    public String toString() {
        return "x:"+x.toString(16)+" y:"+y.toString(16);
    }
}
