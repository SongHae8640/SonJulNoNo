import JoinFormView from "../views/JoinFormView.js";
import LoginFormView from "../views/LoginFormView.js"
import UserModel from "../models/UserModel.js";

const tag = '[UserController]'

export default {
    init(){
        console.log(tag, 'init()')
        if(document.querySelector('#joinForm')){
            JoinFormView.setup(document.querySelector('#joinForm'))
                .on('@join', e => this.onJoin(e.detail.input))
        }else{
            LoginFormView.setup(document.querySelector("#loginForm"))
                .on('@login', e => this.onLogin(e.detail.input))
        }





    },

    onJoin(data){
        console.log(tag, 'onJoin(data)', data)
        UserModel.join(data).then( req =>{
            this.onJoinResult(req)
        }).catch(function (err){
            console.log(tag,"onJoin() error")
        })
    },

    onJoinResult(req){
        console.log(tag, 'onJoinResult(req)',req)
        location.href = req._links.self.href;

    },

    onLogin(data){
        console.log(tag,'onLogin(data)', data)
        UserModel.login(data).then(req =>{
            this.onLoginResult(req)
        }).catch(function (err){
            console.log(tag,"onLogin() error")
        })
    },

    onLoginResult(req){
        console.log(tag, 'onLoginResult(req)',req)
        location.href = req._links.self.href;

    },


}