import View from './View.js'

const tag = '[UpdateView]'

const UpdateView = Object.create(View)


UpdateView.setup = function (el) {
    console.log(tag,"setup() :: el = ", el)
    this.init(el)
    this.id = el.querySelector('#id')
    this.title = el.querySelector('#title')
    this.name = el.querySelector('#name')
    this.content = el.querySelector('#content')
    this.update = el.querySelector('#update')
    this.updateCancel = el.querySelector('#updateCancel')

    this.bindEvent()



    return this
}

UpdateView.bindEvent = function (){
    console.log(tag,'bindEvent()')
    this.update.addEventListener('click', e => this.onClickUpdate())
    this.updateCancel.addEventListener('click', e => this.onClickUpdateCancel())
}

UpdateView.onClickUpdate = function (){
    console.log(tag,'onClickUpdate()')
    var bbs = {
        id : this.id.value,
        title  : this.title.value,
        content : this.content.value,
    }
    this.emit('@update',{input : bbs})
}

UpdateView.onClickUpdateCancel = function (){
    console.log(tag,'onClickUpdateCancel()')
    this.emit('@updateCancel',{input : "글 상세"})

}


export default UpdateView