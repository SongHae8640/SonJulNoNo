import FormView from "../views/FormView.js";
import View from "../views/View.js";
import JoinModel from "../models/JoinModel.js";

const tag = '[UserController]'

export default {
    init(){
        console.log(tag, 'init()')
        FormView.setup(document.querySelector('#joinForm'))
            .on('@join', e => this.onJoin(e.detail.input))


    },

    onJoin(data){
        console.log(tag, 'onJoin()', data)
        JoinModel.join(data).then( req =>{
            this.onJoinResult(req)
        })
    },

    onJoinResult(req){
        console.log(tag, 'onJoinResult() req',req)
        //location.href = req._links.self.href;

    }
}