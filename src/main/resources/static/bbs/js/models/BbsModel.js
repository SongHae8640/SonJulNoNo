const tag = '[UserController]'

const data = [
    {
        id : 1
    },
    {
        id :2
    }
]

export default {
    delete(id){
        console.log(tag, 'delete(id)' ,id)
        return new Promise(function (resolve, reject){
            $.ajax({
                type: "DELETE",
                contentType: "application/json",
                url: "/bbs/"+id,
                dataType: 'json',
                cache: false,
                timeout: 3000,
                success: function (req) {
                    resolve(req)
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                    alert(e.responseText)
                    reject(e)
                }
            });


        })
    },
    update(data){
        console.log(tag, 'update(data)' ,data)
        return new Promise(function (resolve, reject){
            $.ajax({
                type: "PUT",
                contentType: "application/json",
                url: "/bbs/"+data.id,
                data: JSON.stringify(data),
                dataType: 'json',
                cache: false,
                timeout: 3000,
                success: function (req) {
                    resolve(req)
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                    alert(e.responseText)
                    reject(e)
                }
            });


        })
    },

    insert(data){
        console.log(tag, 'update(data)' ,data)
        return new Promise(function (resolve, reject){
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/bbs",
                data: JSON.stringify(data),
                dataType: 'json',
                cache: false,
                timeout: 3000,
                success: function (req) {
                    resolve(req)
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                    alert(e.responseText)
                    reject(e)
                }
            });


        })
    },

}