/**
 * Created by I067382 on 11/22/2016.
 */
function create_counter(initial) {
    var x = initial || 0;
    return {
        inc: function () {
            x += 1;
            return x;
        }
    }
}

var c1 = create_counter();
console.log(c1.inc());
console.log(c1.inc());
console.log(c1.inc());

var c2 = create_counter(10);
console.log(c2.inc());
console.log(c2.inc());
console.log(c2.inc());