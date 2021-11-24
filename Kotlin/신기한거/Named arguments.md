## Named arguments

```kotlin
fun main(args: Array<String>) {
    reformat(
    "String!",
    false,
    upperCaseFirstLetter = false,
    divideByCamelHumps = true,
    '_'
)
}

fun reformat(
    str: String,
    normalizeCase: Boolean = true,
    upperCaseFirstLetter: Boolean = true,
    divideByCamelHumps: Boolean = false,
    wordSeparator: Char = ' ',
) { /*...*/ } 
```

* reformat 함수의 매개변수에 미리 값을 대입할 수 있기 때문에 default data class 값을 설정해주지 않아도 된다.