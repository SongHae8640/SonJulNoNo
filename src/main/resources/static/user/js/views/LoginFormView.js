import View from './View.js'

const tag = '[LoginFormView]'

const LoginFormView = Object.create(View)

LoginFormView.setup = function (el) {
    console.log(tag,"setup() :: el = ", el)
    this.init(el)
    this.name = el.querySelector('#name')
    this.password = el.querySelector('#password')
    this.loginEl = el.querySelector("#login")
    this.joinEl = el.querySelector('#join')

    this.bindEvent()

    this.setTestInputValue()

    return this
}

LoginFormView.bindEvent = function (){
    console.log(tag,'bindEvent()')
    this.on('submit', e => e.preventDefault())
    this.loginEl.addEventListener('click', e => this.onClickLogin())
    this.joinEl.addEventListener('click', e => this.onClickJoin())
}

LoginFormView.onClickLogin = function (){
    console.log(tag,'onClickLogin()')
    var userInfo = {
        name  : this.name.value,
        password : this.password.value,
    }
    this.emit('@login',{input : userInfo})
}

LoginFormView.onClickJoin = function (){
    // go join page
    console.log(tag, 'onClickJoin()')
    location.href = "/user/join"
}

LoginFormView.setTestInputValue = function (){
    console.log(tag, 'setTestInputValue()')
    this.name.value = 'Test'
    this.password.value = '1234'
}

export default LoginFormView