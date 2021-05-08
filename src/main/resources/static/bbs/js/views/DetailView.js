import View from './View.js'

const tag = '[DetailView]'

const DetailView = Object.create(View)


DetailView.setup = function (el) {
    console.log(tag,"setup() :: el = ", el)
    this.init(el)
    this.id = el.querySelector('#bbsId')
    this.preUpdate = el.querySelector('#preUpdate')
    this.delete = el.querySelector('#delete')

    this.bindEvent()



    return this
}

DetailView.bindEvent = function (){
    console.log(tag,'bindEvent()')
    this.preUpdate.addEventListener('click', e => this.onClickPreUpdate())
    this.delete.addEventListener('click', e => this.onClickDelete())
}

DetailView.onClickPreUpdate = function (){
    console.log(tag,'onClickPreUpdate()')
    this.emit('@preUpdate',{input : '글 수정'})
}

DetailView.onClickDelete = function (){
    console.log(tag,'onClickDelete()')
    this.emit('@delete',{input : this.id.value})

}


export default DetailView