import ListView from "../views/ListView.js";
import WriteView from "../views/WriteView.js";


import BbsModel from "../models/BbsModel.js";

const tag = '[ListController]'



export default {
    init(){
        console.log(tag, 'init()')

        ListView.setup(document.querySelector('#listContainer'))
            .on('@preWrite', e => this.changeMode(e.detail.input))

        WriteView.setup(document.querySelector('#writeContainer'))
            .on('@write', e => this.onWrite(e.detail.input))
            .on('@writeCancel', e => this.changeMode(e.detail.input))


        this.changeMode('글 목록')


    },

    changeMode(mode){
        console.log(tag, 'changeMode(mode)', mode)

        if(mode === '글 목록'){
            ListView.show()
            WriteView.hide()

        }else {
            WriteView.show()
            ListView.hide()
        }

    },

    onWrite(data){
        console.log(tag,'onWrite(data)', data)
        BbsModel.insert(data).then(req =>{
            this.onWriteResult(req)
        }).catch(function (err){
            console.log(tag,"onWrite() error")
        })
    },

    onWriteResult(req){
        console.log(tag, 'onWriteResult(req)',req)
        location.href = req._links.self.href;

    },


}