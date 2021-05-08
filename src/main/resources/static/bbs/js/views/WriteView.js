import View from './View.js'

const tag = '[WriteView]'

const WriteView = Object.create(View)


WriteView.setup = function (el) {
    console.log(tag,"setup() :: el = ", el)
    this.init(el)
    this.title = el.querySelector('#title')
    this.content = el.querySelector('#content')
    this.write = el.querySelector('#write')
    this.writeCancel = el.querySelector('#writeCancel')

    this.bindEvent()



    return this
}

WriteView.bindEvent = function (){
    console.log(tag,'bindEvent()')
    this.write.addEventListener('click', e => this.onClickWrite())
    this.writeCancel.addEventListener('click', e => this.onClickWriteCancel())
}

WriteView.onClickWrite = function (){
    console.log(tag,'onClickWrite()')
    var bbs = {
        title  : this.title.value,
        userInfo : {id : 1},
        content : this.content.value,
    }
    this.emit('@write',{input : bbs})
}

WriteView.onClickWriteCancel = function (){
    console.log(tag,'onClickWriteCancel()')
    this.emit('@writeCancel',{input : "글 목록"})

}


export default WriteView