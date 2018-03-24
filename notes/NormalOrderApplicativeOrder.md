
## Difference between Lazy evaluation and normal order application
- Lazy evaluation evaluates a term at most once, while normal order would evaluate it as often as it appears.
  So for example if you have f(x) = x+x and you call it as f(g(42)) then g(42) is called once under lazy evaluation 
  or applicative order, but twice under normal order. 
- Eager evaluation and applicative order are synonymous, at least when using the definition of applicative order.

- lazy evaluation and NormalOrderEvaluation are somewhat two different: In lazy evaluation, evaluation of the argument is deferred until it is needed, 
  at which point the argument is evaluated and its result saved (memoized). Further uses of the argument in the function use the computed value. 
  The C/C++ operators ||, &&, and ? : are both examples of lazy evaluation (each argument is evaluated at most once, possibly not at all).

  NormalOrderEvaluation, on the other hand, re-evaluates the expression each time it is used. Normal-order evaluation can take much longer than applicative order 
  evaluation, and can cause side effects to happen more than once.

  If the argument is invariant and has no side effects, the only difference between the two is performance. Indeed, in a purely functional language, lazy eval can 
  be viewed as an optimization of normal-order evaluation. With side effects present, or expressions which can return a different value when re-evaluated, the two 
  have different behavior; normal order eval in particular has a bad reputation in procedural languages due to the difficulty of reasoning about such programs 
  without Referential Transparency, Should also be noted that strict-order evaluation (as well as lazy evaluation) can be achieved in a language which supports 
  normal-order evaluation via explicit memoing. The opposite isn't true.
  So:
  - Lazy evaluation combines normal-order evaluation and sharing:
     * Never evaluate something until you have to (normal-order)
     * Never evaluate something more than once (sharing) 

## Difference between Normal order evaluation and call-by-name evaluation

- Normal order evaluation and call-by-name evaluation are not quite the same thing. In normal order evaluation, the outermost function is 
  evaluated before any of its arguments, and those arguments are evaluated only if needed. In call-by-name evaluation, the arguments are 
  effectively copied into the body of the outermost function and then that function is evaluated. In both cases, the outermost function is 
  technically evaluated before the arguments, but in pure call-by-name, the arguments are evaluated each time they are used (either zero,
  one, or many times). In normal order, the function arguments are evaluated at the very least only when first needed (typically zero or one times).
(See [this answer](https://softwareengineering.stackexchange.com/questions/193442/are-normal-order-and-call-by-name-the-same-thing))
