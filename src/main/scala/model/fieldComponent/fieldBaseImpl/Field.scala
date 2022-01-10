package model.fieldComponent.fieldBaseImpl

import model.fieldComponent.{FieldInterface, MatrixInterface}
import com.google.inject.Inject

case class Field @Inject() (var matrix: MatrixInterface[Char]) extends FieldInterface[Char]:
    val eol = "\n"
    
    def edgetop = " ___ " + "    ___ " * (matrix.col/2) + eol
    def edgebot = " " + "   \\___/" * (matrix.col/2) + eol

    override def fillAll(c: Char): MatrixInterface[Char] = matrix.fillAll(c)

    override def place(c: Char, x: Int, y: Int): MatrixInterface[Char] = {
        matrix.fill(c, x, y)
    }

    def bot(line: Int): String = {
        var res = "\\___/"
        
        matrix.matrix(line).zipWithIndex.foreach(
        (x, i) => if i % 2 != 0 && i >= 1 && i < matrix.col then res += " " + x.toString + " \\___/")

        res + "\n"
    }

    def top(line: Int): String = {
        var res = "/ " + matrix.matrix(line)(0).toString + " \\"

        matrix.matrix(line).zipWithIndex.foreach(
        (x, i) => if i % 2 == 0 && i >= 2 then res += "___/ " + x + " \\")

        res + "\n"
    }

    override def reset = copy(matrix.fillAll(' '))
    
    override def field = {
        var res = eol + edgetop
        for (l <- 0 until matrix.row) res += (top(l) + bot(l))
        res += edgebot
        res
    }
