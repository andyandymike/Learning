/**
 * Created by I067382 on 11/22/2016.
 */
function getAge() {
    var y = new Date().getFullYear();
    return y - this.birth;
}

var xiaoming = {
    name: '小明',
    birth: 1990,
    age: getAge
};

console.log(xiaoming.age()); // 25, 正常结果
console.log(getAge()); // NaN