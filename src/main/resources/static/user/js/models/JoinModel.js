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
    join(data){
        console.log(tag, 'join :: data =' ,data)

        return new Promise(function (resolve, reject){
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/user",
                data: JSON.stringify(data),
                dataType: 'json',
                cache: false,
                timeout: 3000,
                success: function (req) {
                    resolve(req)
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                    reject(e)
                }
            });


        })





    }

}