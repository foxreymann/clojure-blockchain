const prefix = require('superagent-prefix')
const req = require('superagent-use')(require('superagent'))
req.use(prefix('http://localhost:3000'))

;(async () => {
  try {
    res = await req.get('/chain')
    console.log(res.body)

    res = await req.get('/mine')
    console.log(res.body)

    res = await req.post('/transactions/new').send({
     sender: "A",
     recipient: "B",
     amount: 5
    })
    console.log(res.body)

    res = await req.get('/chain')
    console.log(res.body)

  } catch (err) {
    console.log(err)
  }
})()
