package scala

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class HexSpecTest extends AnyWordSpec {
    info("Note that HexFields have to have an uneven number of columns!")
    "A Hex" when {
    
        "created as 6 - 9 Grid" should {
        
            val hex = new HexField(9, 6)

            "start with the top" in {
                hex.edgetop should be(new HexField(hex.col, 1).edgetop);
            }
            "have lines" in {
                "\n" + hex.edgetop + hex.top(0) + hex.bot(0) + hex.edgebot should be(new HexField( hex.col, 1).field)
            }
            "be equal to a field the same size" in {
                hex.field should be(new HexField(hex.col, hex.lines).field)
            }
        }

        "created as default Grid" should {
        
            val hex = new HexField()

            "start with the top" in {
                hex.edgetop should be(new HexField(hex.col, 1).edgetop);
            }
            "have lines" in {
                "\n" + hex.edgetop + hex.top(0) + hex.bot(0) + hex.edgebot should be(new HexField(hex.col, 1).field)
            }
            "be equal to a field with the size: 9 - 6" in {
                hex.field should be(new HexField(9, 6).field)
            }
            "be empty in every Cell at the beginning" in {
                hex.matrix.contains('X') should be(false)
                hex.matrix.contains('O') should be(false)
                hex.field should be(new HexField(9, 6).field)
            }
            "be empty in every Cell at the beginning" in {
                hex.matrix.contains('X') should be(false)
                hex.matrix.contains('O') should be(false)
                hex.field should be(new HexField(6, 9).field)
            }
        }

        "created as empty Grid" should {

            val hex = new HexField(0, 0)

            "not contain a top" in {
                hex.edgetop should be("");
            }
            "not have any lines" in {
                hex.edgetop + hex.top(0) + hex.bot(0) should be(new HexField(0, 0).field)
            }
            "be empty when printed completly" in {
                hex.field should be("")
            }
        }

        "created as Single Cell" should {

            val hex = new HexField(1, 1)

            "contain a Space when empty" in {
                hex.matrix(0)(0) should be(' ')
            }
            "contain a X" in {
                hex.placeX(0, 0)
                hex.matrix(0)(0) should be('X')
            }
            "contain a O" in {
                hex.placeO(0, 0)
                hex.matrix(0)(0) should be('O')
            }
        }

        "matching Input" should {
            val hex = new HexField()
            val reg = ("[0-" + (hex.col - 1) + "]\\s[0-" + (hex.lines - 1) + "]\\s[XO]").r
            "\"Wrong Input\"" in {
                matchReg(reg.findFirstIn("wrong")) should be("Wrong Input")
            }
            "be \"\"" in {
                matchReg(reg.findFirstIn("0 0 X")) should be("0 0 X")
            }
        }
    }
}