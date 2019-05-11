const prefix = require('superagent-prefix')
const req = require('superagent-use')(require('superagent'))
req.use(prefix('http://localhost:3000'))

;(async () => {
  try {
    let res = await req.get('/foxjson')
    console.log(res.body)

    res = await req.get('/chain')
    console.log(res.body)

  } catch (err) {
    console.log(err)
  }
})()
