import DetailView from "../views/DetailView.js";
import UpdateView from "../views/UpdateView.js";

import BbsModel from "../models/BbsModel.js";

const tag = '[DetailController]'



export default {
    init(){
        console.log(tag, 'init()')

        DetailView.setup(document.querySelector('#detailContainer'))
            .on('@delete', e => this.onDelete(e.detail.input))
            .on('@preUpdate', e => this.changeMode(e.detail.input))

        UpdateView.setup(document.querySelector('#updateContainer'))
            .on('@update', e => this.onUpdate(e.detail.input))
            .on('@updateCancel', e => this.changeMode(e.detail.input))


        this.changeMode('글 상세')


    },

    changeMode(mode){
        console.log(tag, 'changeMode(mode)', mode)

        if(mode === '글 상세'){
            UpdateView.hide()
            DetailView.show()
        }else {
            DetailView.hide()
            UpdateView.show()
        }

    },

    onDelete(id){
        console.log(tag, 'onDelete(id)', id)
        BbsModel.delete(id).then( req =>{
            this.onDeleteResult(req)
        }).catch(function (err){
            console.log(tag,"onDelete() error")
        })
    },

    onDeleteResult(req){
        console.log(tag, 'onDeleteResult(req)',req)
        location.href = req._links.self.href;

    },

    onUpdate(data){
        console.log(tag,'onUpdate(data)', data)
        BbsModel.update(data).then(req =>{
            this.onUpdateResult(req)
        }).catch(function (err){
            console.log(tag,"onUpdate() error")
        })
    },

    onUpdateResult(req){
        console.log(tag, 'onUpdateResult(req)',req)
        location.href = req._links.self.href;

    },


}