import View from './View.js'

const tag = '[ListView]'

const ListView = Object.create(View)


ListView.setup = function (el) {
    console.log(tag,"setup() :: el = ", el)
    this.init(el)
    this.preWrite = el.querySelector('#preWrite')

    this.bindEvent()



    return this
}

ListView.bindEvent = function (){
    console.log(tag,'bindEvent()')
    this.preWrite.addEventListener('click', e => this.onClickPreWrite())
}

ListView.onClickPreWrite = function (){
    console.log(tag,'onClickPreWrite()')
    this.emit('@preWrite',{input : '글 작성'})
}


export default ListView