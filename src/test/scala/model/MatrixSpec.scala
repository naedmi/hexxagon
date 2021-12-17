package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.annotation.varargs
import util.SetHandling.DefaultSetHandler
import model.fieldComponent.fieldBaseImpl.Matrix

class MatrixSpec extends AnyWordSpec {
    "A Matrix" when {
        "initialized" should {
            "contain only ' '" in {
                val matrix = new Matrix(9, 6)
                matrix should be (new Matrix(Vector.fill[Char](6, 9)(' '), 0, 0))
                matrix.matrix.filter(_.contains(' ')) should be(Vector.fill[Char](matrix.row, matrix.col)(' '))
                matrix.matrix.filter(_.contains('X')) should be(Vector())
                matrix.matrix.filter(_.contains('O')) should be(Vector())
            }
            "have its counters on 0" in {
                val matrix = new Matrix(9, 6)
                matrix.Xcount should be (0)
                matrix.Ocount should be (0)
            }
        }
        "initialized as a 9 - 6 Grid" should {
            "be the same size as an 8 - 6 Grid" in {
                new Matrix(9, 6) should be (new Matrix(8, 6))
            }
        }
        "having a stone placed" should {
            var matrix = new Matrix(5, 4)
            matrix = matrix.fill('O', 0, 0)
            matrix = matrix.fill('X', 1, 0)
            matrix = matrix.fill('O', 2, 0)
            matrix = matrix.fill(' ', 4, 3)
            "contain stone in the cell" in {
                matrix.Ocount should be (2)
                matrix.matrix.flatten.groupBy(identity).map(t => (t._1, t._2.length)).get('O') match {
                    case Some(i) => i should be (matrix.Ocount)
                    case None => matrix.Ocount should be (0)
                }
                matrix.Xcount should be (1)
                matrix.matrix.flatten.groupBy(identity).map(t => (t._1, t._2.length)).get('X') match {
                    case Some(i) => i should be (matrix.Xcount)
                    case None => matrix.Xcount should be (0)
                }
                matrix.cell(0, 0) should be ('X')
                matrix.cell(1, 0) should be ('O')
                matrix.cell(2, 0) should be ('O')
                matrix.cell(4, 3) should be (' ')
            }
        }
        "placing a stone on top of a stone" should {
            var matrix = new Matrix(5, 4)
            "counters should substract 1" in {
                matrix = matrix.fill('O', 2, 2)
                matrix.Ocount should be (1)
                matrix.Xcount should be (0)
                matrix = matrix.fill('X', 2, 2)
                matrix.Ocount should be (0)
                matrix.Xcount should be (1)
            }
        }
        "being filled with a stone" should {
            var matrix = new Matrix(5, 5)
            "should only contain 'X'" in {
                matrix = matrix.fillAll('X')
                matrix.Xcount should be (matrix.row * matrix.col)
                matrix.Ocount should be (0)
                matrix.matrix.flatten.contains(' ') should be (false)
                matrix.matrix.flatten.contains('O') should be (false)
                matrix.matrix.flatten.contains('X') should be (true)
            }
            "should only contain 'O'" in {
                matrix = matrix.fillAll('O')
                matrix.Ocount should be (matrix.row * matrix.col)
                matrix.Xcount should be (0)
                matrix.matrix.flatten.contains(' ') should be (false)
                matrix.matrix.flatten.contains('X') should be (false)
                matrix.matrix.flatten.contains('O') should be (true)
            }
            "fill results in new matrix" in {
                var mat = new Matrix(1, 1)
                mat.fillAll(' ') should be (new Matrix(1, 1))
                mat = mat.fill('X', 0, 0)
                mat.cell(0, 0) should be ('X')
                mat = mat.fillAll(' ')
                mat.cell(0, 0) should be (' ')
            }
            "should only contain 'P'" in {
                matrix = matrix.fillAll('P')
                matrix.Ocount should be (0)
                matrix.Xcount should be (0)
                matrix.matrix.flatten.contains(' ') should be (false)
                matrix.matrix.flatten.contains('X') should be (false)
                matrix.matrix.flatten.contains('O') should be (false)
            }
        }
    }
}