import View from './View.js'

const tag = '[FormView]'

const FormView = Object.create(View)

FormView.setup = function (el) {
    console.log(tag,"setup() :: el = ", el)
    this.init(el)
    this.name = el.querySelector('#name')
    this.email = el.querySelector('#email')
    this.password = el.querySelector('#password')
    this.rePassword = el.querySelector('#rePassword')
    this.joinEl = el.querySelector('#join')

    this.bindEvent()

    this.setTestInputValue()

    return this
}

FormView.bindEvent = function (){
    console.log(tag,'bindEvent()')
    this.on('submit', e => e.preventDefault())
    this.joinEl.addEventListener('click', e => this.onClickJoin())
}

FormView.onClickJoin = function (){
    console.log(tag,'onClickJoin()')
    var userInfo = {
                    name  : this.name.value,
                    email : this.email.value,
                    password : this.password.value,
                    rePassword : this.rePassword.value
                    }
    this.emit('@join',{input : userInfo})

}

FormView.setTestInputValue = function (){
    console.log(tag, 'setTestInputValue()')
    this.name.value = 'Test'
    this.email.value = 'test@naver.com'
    this.password.value = '1234'
    this.rePassword.value = '1234'
}

export default FormView