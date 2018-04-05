module Chapter1 where

-- 1.3

{--
ex1_3 :: (Num a, Ord a) => a -> a -> a -> a
ex1_3 x y z
    | z < x && z < y = x * x + y * y
    | y < x && y < z = x * x + z * z
    | otherwise      = y * y + z * z

this one fails for ex1_3 4 3 3 and return 18 instead of 25
--}

-- this version works for all cases
ex1_3 :: (Num a, Ord a) => a -> a -> a -> a
ex1_3 x y z
  | x < y = if x < z
            then y * y + z * z
            else x * x + y * y
  | otherwise = if y < z
                then x * x + z * z
                else x * x + y * y

average :: Floating a => a -> a -> a
average x y = (x + y) / 2

goodEnough :: (Ord a, Floating a) => a -> a -> Bool
goodEnough oldGuess newGuess = (abs ((oldGuess - newGuess) / oldGuess)) < 0.001

sqrtRel :: (Ord a, Floating a) => a -> a
sqrtRel x = sqrtIter 1.0 2.0 x
  where sqrtIter g1 g2 x = if goodEnough g1 g2
          then g1
          else (sqrtIter (improve g1) g1 x)
        improve g1 = average g1 (x / g1)


cb :: (Ord a, Floating a) => a -> a
cb x = cbIter 1.0 x
    where
        cbIter guess x = if goodEnough guess
            then guess
            else cbIter (improve guess) x
        goodEnough guess = abs (guess * guess * guess / x - 1) < 1e-8
        improve guess = (2 * guess + x / (guess * guess)) / 3


-- Ex 1.12
-- Pascal Triangle

-- recursive process
pascal :: Int -> Int -> Int
pascal _ 0 = 1
pascal 0 _ = 1
pascal row col
  | row < col = error "Error "
  | col == row = 1
  | otherwise = pascal (row - 1) col + pascal (row - 1) (col - 1)


-- 1.3.1
sumRec :: (Ord a, Num b) => (a -> b) -> a -> (a -> a) -> a -> b
sumRec term a next b
  | a > b = 0
  | otherwise = term a + sumRec term (next a) next b

cube :: (Ord a, Num a) => a -> a
cube x = x * x * x

sumCubes :: (Ord a, Num a) => a -> a -> a
sumCubes a b = sumRec cube a (+1) b

sumIntegers :: (Ord b, Num b) => b -> b -> b
sumIntegers a b = sumRec id a (+1) b

piSum a b = sumRec piTerm a piNext b
  where
    piTerm x = 1.0 / (x * (x + 2))
    piNext x = x + 4

-- what's cool here is that the functions passed can do really sophiscated things but the pattern
-- is the same
integral :: (Ord a, Fractional a) => (a -> a) -> a -> a -> a -> a
integral f a b dx = (sumRec f (a + (dx / 2)) addDx b) * dx
  where
    addDx x = x + dx

-- 1.29
simpsonRule :: Fractional a => (a -> a) -> a -> a -> Int -> a
simpsonRule f a b n = (sumRec term 0 (+1) n) * (h/3)
  where
    term k
      | k == 0 || k == n = f a
      | even k = f (a + ((fromIntegral k) * h)) * 2
      | odd k = f (a + ((fromIntegral k) * h)) * 4
    h = (b - a) / fromIntegral n

-- 1.45

compose f g = \x -> f (g x)

repeated f n
  | n == 1 = f
  | otherwise = compose f (repeated f (n - 1))

averageDump :: Fractional a => (a -> a) -> (a -> a)
averageDump f = \x -> (x + (f x)) / 2

fixedPoint f guess = try guess
  where
    tolerance = 0.00001
    closeEnough x y = abs (x - y) < tolerance
    try s
          | closeEnough s next = next
          | otherwise = try next
          where
            next = f s

log2 :: Integral a => a -> a
log2 n = iter n 0
    where iter n result = if n == 1
            then result
            else iter (n `div` 2) (result + 1)

nthRoot :: (Integral a, Ord b, Fractional b) => a -> b -> b
nthRoot n x = fixedPoint (repeated (averageDump f) k) 1.0
  where f = \y -> x / y ^ (n - 1)
        k = log2 n
