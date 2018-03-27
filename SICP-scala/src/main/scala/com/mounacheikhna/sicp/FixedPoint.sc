
def fixedPoint(f: Double => Double, firstGuess: Double): Double = {
  val tolerance = 0.0001
  val maxIterations = 30

  var guess = firstGuess
  for (_ <- 1 to maxIterations) {
    if (Math.abs(guess - f(guess)) < tolerance) {
      return guess
    }

    guess = f(guess)
    println(guess)
  }

  /* We return NaN if we didn't converge in time. */
  Double.NaN
}

fixedPoint(x => 1 + 1/x, 1.0)

(1 + Math.sqrt(5))/2

fixedPoint(x => Math.log(1000) / Math.log(x), 1.5)

fixedPoint(Math.cos, 1.0)

fixedPoint(y => 2/y, 1.0)

fixedPoint(y => (2/y + y)/2, 1.0)

def averageDamp(f: Double => Double): Double => Double =
  x => (x + f(x)) / 2

fixedPoint(averageDamp(x => Math.log(1000) / Math.log(x)), 1.5)

fixedPoint(averageDamp(Math.cos), 1.0)

def sqrt(x: Double): Double = fixedPoint(averageDamp(y => x/y), 1.0)

sqrt(2)

