package controllers

import play.api._
import play.api.mvc._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

class Application extends Controller {

  def index = Action {

    Ok(views.html.index())
  }

  def shots = Action {
    val logFile = "Curry.csv" // Should be some file on your system

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = SparkContext.getOrCreate(conf)
    sc.setLogLevel("WARN")
    val logData = sc.textFile(logFile, 1).cache()

    val numTotal = logData.filter(line => line.length()>0).count().toFloat
    val numMade = logData.filter(line => line.split(",")(9).contains("Made Shot")).count().toFloat
    println(s"Total Attempt: "+numTotal+",Made: "+numMade+",Overall percentage: " + numMade/numTotal)


    // restricted
    val RATotal = logData.filter(line => line.split(",")(29).contains("Restricted Area")).count()
    val RAMade = logData.filter(line => line.split(",")(29).contains("Restricted Area")
      && line.split(",")(9).contains("Made Shot")).count().toFloat
    println(s"RA Attempt: "+RATotal+",Made: "+RAMade+", percentage: " + RAMade/RATotal)

    // Paint
    val PaintTotal = logData.filter(line => line.split(",")(29).contains("In The Paint")).count()
    val PaintMade = logData.filter(line => line.split(",")(29).contains("In The Paint")
      && line.split(",")(9).contains("Made Shot")).count().toFloat
    println(s"Paint Attempt: "+PaintTotal+",Made: "+PaintMade+", percentage: " + PaintMade/PaintTotal)

    // Mid-Range-left
    val MidLeftTotal = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(28).contains("(L)")).count().toFloat
    val MidLeftMade = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(9).contains("Made Shot")
      && line.split(",")(28).contains("(L)")).count().toFloat
    println(s"Left Mid-range Attempt: "+MidLeftTotal+",Made: "+MidLeftMade+", percentage: " + MidLeftMade/MidLeftTotal)

    // Mid-Range-right
    val MidRightTotal = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(28).contains("(R)")).count().toFloat
    val MidRightMade = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(9).contains("Made Shot")
      && line.split(",")(28).contains("(R)")).count().toFloat
    println(s"Right Mid-range Attempt: "+MidRightTotal+",Made: "+MidRightMade+", percentage: " + MidRightMade/MidRightTotal)

    // Mid-Range-Center
    val MidCenterTotal = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(28).contains("(C)")).count().toFloat
    val MidCenterMade = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(9).contains("Made Shot")
      && line.split(",")(28).contains("(C)")).count().toFloat
    println(s"Center Mid-range Attempt: "+MidCenterTotal+",Made: "+MidCenterMade+", percentage: " + MidCenterMade/MidCenterTotal)

    // Mid-Range-Center-left
    val MidCenterLeftTotal = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(28).contains("(LC)")).count().toFloat
    val MidCenterLeftMade = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(9).contains("Made Shot")
      && line.split(",")(28).contains("(LC)")).count().toFloat
    println(s"Center Left Mid-range Attempt: "+MidCenterLeftTotal+",Made: "+MidCenterLeftMade+", percentage: " + MidCenterLeftMade/MidCenterLeftTotal)

    // Mid-Range-Center-right
    val MidCenterRightTotal = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(28).contains("(RC)")).count().toFloat
    val MidCenterRightMade = logData.filter(line => line.split(",")(29).contains("Mid-Range")
      && line.split(",")(9).contains("Made Shot")
      && line.split(",")(28).contains("(RC)")).count().toFloat
    println(s"Center right Mid-range Attempt: "+MidCenterRightTotal+",Made: "+MidCenterRightMade+", percentage: " + MidCenterRightMade/MidCenterRightTotal)

    // Three-Point-center-left
    val CenterLeftThreePTTotal = logData.filter(line => line.split(",")(27).contains("3PT Field Goal")
      && line.split(",")(28).contains("(LC)")).count().toFloat
    val CenterLeftThreePTMade = logData.filter(line => line.split(",")(27).contains("3PT Field Goal")
      && line.split(",")(28).contains("(LC)")
      && line.split(",")(9).contains("Made Shot")).count().toFloat
    println(s"Center Left 3-Pt Attempt: "+CenterLeftThreePTTotal+",Made: "+CenterLeftThreePTMade+", percentage: " + CenterLeftThreePTMade/CenterLeftThreePTTotal)

    // Three-Point-center-right
    val CenterRightThreePTTotal = logData.filter(line => line.split(",")(27).contains("3PT Field Goal")
      && line.split(",")(28).contains("(RC)")).count().toFloat
    val CenterRightThreePTMade = logData.filter(line => line.split(",")(27).contains("3PT Field Goal")
      && line.split(",")(28).contains("(RC)")
      && line.split(",")(9).contains("Made Shot")).count().toFloat
    println(s"Center Right 3-Pt Attempt: "+CenterRightThreePTTotal+",Made: "+CenterRightThreePTMade+", percentage: " + CenterRightThreePTMade/CenterLeftThreePTTotal)

    // Three-Point-center
    val CenterThreePTTotal = logData.filter(line => line.split(",")(27).contains("3PT Field Goal")
      && line.split(",")(28).contains("(C)")).count().toFloat
    val CenterThreePTMade = logData.filter(line => line.split(",")(27).contains("3PT Field Goal")
      && line.split(",")(28).contains("(C)")
      && line.split(",")(9).contains("Made Shot")).count().toFloat
    println(s"Center 3-Pt Attempt: "+CenterThreePTTotal+",Made: "+CenterThreePTMade+", percentage: " + CenterThreePTMade/CenterThreePTTotal)

    // Left-corner Three-Point
    val LeftCornerThreePTTotal = logData.filter(line => line.split(",")(29).contains("Left Corner")).count().toFloat
    val LeftCornerThreePTMade = logData.filter(line => line.split(",")(29).contains("Left Corner")
      && line.split(",")(9).contains("Made Shot")).count().toFloat
    println(s"Left corner 3-Pt Attempt: "+LeftCornerThreePTTotal+",Made: "+LeftCornerThreePTMade+", percentage: " + LeftCornerThreePTMade/LeftCornerThreePTTotal)

    // Right-corner Three-Point
    val RightCornerThreePTTotal = logData.filter(line => line.split(",")(29).contains("Right Corner")).count().toFloat
    val RightCornerThreePTMade = logData.filter(line => line.split(",")(29).contains("Right Corner")
      && line.split(",")(9).contains("Made Shot")).count().toFloat
    println(s"Right corner 3-Pt Attempt: "+RightCornerThreePTTotal+",Made: "+RightCornerThreePTMade+", percentage: " + RightCornerThreePTMade/RightCornerThreePTTotal)


    Ok {
      views.html.shots(numMade/numTotal, RAMade/RATotal, PaintMade/PaintTotal, MidLeftMade/MidLeftTotal,
        MidRightMade/MidRightTotal,
        MidCenterMade/MidCenterTotal, MidCenterLeftMade/MidCenterLeftTotal, MidCenterRightMade/MidCenterRightTotal,
        LeftCornerThreePTMade/LeftCornerThreePTTotal, RightCornerThreePTMade/RightCornerThreePTTotal,
        CenterLeftThreePTMade/CenterLeftThreePTTotal, CenterRightThreePTMade/CenterRightThreePTTotal,
        CenterThreePTMade/CenterThreePTTotal)
    }
  }


  def shotsForLBJ = Action {
    val logFile = "JamesLebron.csv" // Should be some file on your system

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = SparkContext.getOrCreate(conf)
    sc.setLogLevel("WARN")
    val logData = sc.textFile(logFile, 1).cache()

    val numTotal = logData.filter(line => line.length()>0).count().toFloat
    val numMade = logData.filter(line => line.split(",")(11).contains("Made Shot")).count().toFloat
    println(s"Total Attempt: "+numTotal+",Made: "+numMade+",Overall percentage: " + numMade/numTotal)


    // restricted
    val RATotal = logData.filter(line => line.split(",")(14).contains("Restricted Area")).count()
    val RAMade = logData.filter(line => line.split(",")(14).contains("Restricted Area")
      && line.split(",")(11).contains("Made Shot")).count().toFloat
    println(s"RA Attempt: "+RATotal+",Made: "+RAMade+", percentage: " + RAMade/RATotal)

    // Paint
    val PaintTotal = logData.filter(line => line.split(",")(14).contains("In The Paint")).count()
    val PaintMade = logData.filter(line => line.split(",")(14).contains("In The Paint")
      && line.split(",")(11).contains("Made Shot")).count().toFloat
    println(s"Paint Attempt: "+PaintTotal+",Made: "+PaintMade+", percentage: " + PaintMade/PaintTotal)

    // Mid-Range-left
    val MidLeftTotal = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(15).contains("(L)")).count().toFloat
    val MidLeftMade = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(11).contains("Made Shot")
      && line.split(",")(15).contains("(L)")).count().toFloat
    println(s"Left Mid-range Attempt: "+MidLeftTotal+",Made: "+MidLeftMade+", percentage: " + MidLeftMade/MidLeftTotal)

    // Mid-Range-right
    val MidRightTotal = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(15).contains("(R)")).count().toFloat
    val MidRightMade = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(11).contains("Made Shot")
      && line.split(",")(15).contains("(R)")).count().toFloat
    println(s"Right Mid-range Attempt: "+MidRightTotal+",Made: "+MidRightMade+", percentage: " + MidRightMade/MidRightTotal)

    // Mid-Range-Center
    val MidCenterTotal = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(15).contains("(C)")).count().toFloat
    val MidCenterMade = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(11).contains("Made Shot")
      && line.split(",")(15).contains("(C)")).count().toFloat
    println(s"Center Mid-range Attempt: "+MidCenterTotal+",Made: "+MidCenterMade+", percentage: " + MidCenterMade/MidCenterTotal)

    // Mid-Range-Center-left
    val MidCenterLeftTotal = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(15).contains("(LC)")).count().toFloat
    val MidCenterLeftMade = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(11).contains("Made Shot")
      && line.split(",")(15).contains("(LC)")).count().toFloat
    println(s"Center Left Mid-range Attempt: "+MidCenterLeftTotal+",Made: "+MidCenterLeftMade+", percentage: " + MidCenterLeftMade/MidCenterLeftTotal)

    // Mid-Range-Center-right
    val MidCenterRightTotal = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(15).contains("(RC)")).count().toFloat
    val MidCenterRightMade = logData.filter(line => line.split(",")(14).contains("Mid-Range")
      && line.split(",")(11).contains("Made Shot")
      && line.split(",")(15).contains("(RC)")).count().toFloat
    println(s"Center right Mid-range Attempt: "+MidCenterRightTotal+",Made: "+MidCenterRightMade+", percentage: " + MidCenterRightMade/MidCenterRightTotal)

    // Three-Point-center-left
    val CenterLeftThreePTTotal = logData.filter(line => line.split(",")(13).contains("3PT Field Goal")
      && line.split(",")(15).contains("(LC)")).count().toFloat
    val CenterLeftThreePTMade = logData.filter(line => line.split(",")(13).contains("3PT Field Goal")
      && line.split(",")(15).contains("(LC)")
      && line.split(",")(11).contains("Made Shot")).count().toFloat
    println(s"Center Left 3-Pt Attempt: "+CenterLeftThreePTTotal+",Made: "+CenterLeftThreePTMade+", percentage: " + CenterLeftThreePTMade/CenterLeftThreePTTotal)

    // Three-Point-center-right
    val CenterRightThreePTTotal = logData.filter(line => line.split(",")(13).contains("3PT Field Goal")
      && line.split(",")(15).contains("(RC)")).count().toFloat
    val CenterRightThreePTMade = logData.filter(line => line.split(",")(13).contains("3PT Field Goal")
      && line.split(",")(15).contains("(RC)")
      && line.split(",")(11).contains("Made Shot")).count().toFloat
    println(s"Center Right 3-Pt Attempt: "+CenterRightThreePTTotal+",Made: "+CenterRightThreePTMade+", percentage: " + CenterRightThreePTMade/CenterLeftThreePTTotal)

    // Three-Point-center
    val CenterThreePTTotal = logData.filter(line => line.split(",")(13).contains("3PT Field Goal")
      && line.split(",")(15).contains("(C)")).count().toFloat
    val CenterThreePTMade = logData.filter(line => line.split(",")(13).contains("3PT Field Goal")
      && line.split(",")(15).contains("(C)")
      && line.split(",")(11).contains("Made Shot")).count().toFloat
    println(s"Center 3-Pt Attempt: "+CenterThreePTTotal+",Made: "+CenterThreePTMade+", percentage: " + CenterThreePTMade/CenterThreePTTotal)

    // Left-corner Three-Point
    val LeftCornerThreePTTotal = logData.filter(line => line.split(",")(14).contains("Left Corner")).count().toFloat
    val LeftCornerThreePTMade = logData.filter(line => line.split(",")(14).contains("Left Corner")
      && line.split(",")(11).contains("Made Shot")).count().toFloat
    println(s"Left corner 3-Pt Attempt: "+LeftCornerThreePTTotal+",Made: "+LeftCornerThreePTMade+", percentage: " + LeftCornerThreePTMade/LeftCornerThreePTTotal)

    // Right-corner Three-Point
    val RightCornerThreePTTotal = logData.filter(line => line.split(",")(14).contains("Right Corner")).count().toFloat
    val RightCornerThreePTMade = logData.filter(line => line.split(",")(14).contains("Right Corner")
      && line.split(",")(11).contains("Made Shot")).count().toFloat
    println(s"Right corner 3-Pt Attempt: "+RightCornerThreePTTotal+",Made: "+RightCornerThreePTMade+", percentage: " + RightCornerThreePTMade/RightCornerThreePTTotal)


    Ok {
      views.html.shots(numMade/numTotal, RAMade/RATotal, PaintMade/PaintTotal, MidLeftMade/MidLeftTotal,
        MidRightMade/MidRightTotal,
        MidCenterMade/MidCenterTotal, MidCenterLeftMade/MidCenterLeftTotal, MidCenterRightMade/MidCenterRightTotal,
        LeftCornerThreePTMade/LeftCornerThreePTTotal, RightCornerThreePTMade/RightCornerThreePTTotal,
        CenterLeftThreePTMade/CenterLeftThreePTTotal, CenterRightThreePTMade/CenterRightThreePTTotal,
        CenterThreePTMade/CenterThreePTTotal)
    }
  }

}