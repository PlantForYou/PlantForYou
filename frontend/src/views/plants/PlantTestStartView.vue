<template>
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link
    href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap"
    rel="stylesheet"
  />
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
    crossorigin="anonymous"
  />

  <!-- <TheDarkHeader></TheDarkHeader> -->
  <body style="padding: 10% 10%">
    <div class="container">
      <section id="main" class="mx-auto my-5 py-5 px-3">
        <h1>내 운명의 반려식물은?</h1>
        <div class="col-lg-6 col-md-8 col-sm-10 col-12 mx-auto">
          <img src="@/assets/plants.png" alt="mainImage" class="img-fluid" />
        </div>
        <p>
          내 운명의 반려식물은 무엇일까? <br />
          두구두구 나에게 딱맞는 반려식물을 만나보세요!
        </p>
        <button type="button" class="btn btn-warning" @click="start">
          시작하기
        </button>
      </section>
    </div>
  </body>
</template>
<script>
// import TheDarkHeader from '@/layouts/TheDarkHeader.vue'

export default {
  // components: {
  //   TheDarkHeader
  // },
  methods: {
    start() {
      this.$store.commit('init')
      this.$router.push({ name: 'PlantTestView' })
    }
  }
}
</script>

<style scoped>
#main {
  background-color: whitesmoke;
  width: 80%;
  text-align: center;
  border-radius: 20px;
  left: 50%;
  top: 50%;
}

h1 {
  font-size: 42px;
  padding-top: 20px;
}

.container img {
  padding: 10% 30% 10% 30%;
}

p {
  font-size: 23px;
}

button {
  margin-top: 5px;
}

.container .main .img-fluid {
  width: 100px;
  height: 200px;
}

body {
  background-image: url('@/assets/fern-1250903.jpg');
}

* {
  font-family: 'Cute Font', cursive;
}
</style>
