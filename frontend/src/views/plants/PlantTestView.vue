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

  <div>
    <!-- <TheDarkHeader></TheDarkHeader> -->

    <div id="backImg" style="padding: 10% 10%">
      <div class="status mx-auto mt-5">
        <div class="statusBar" :style="statusStyle"></div>
      </div>

      <div class="qBox mt-5 my-2 pt-3 py-2 mx-auto">
        {{ plantQues.question }}
      </div>
      <div class="answerBox">
        <button
          class="answerList my-3 py-3 mx-auto fadeIn"
          @click="setChoice(option.score)"
          :key="option.plantQuesOptionId"
          v-for="option in plantQues.optionList"
        >
          {{ option.option }}
        </button>
      </div>

      <div class="g-2">
        <button
          class="btn btn-prev"
          v-if="plantQuesId !== 1"
          @click.prevent="prev"
        >
          이전
        </button>
      </div>
    </div>
  </div>
</template>

<script>
// import TheDarkHeader from '@/layouts/TheDarkHeader.vue'
import * as plantApi from '@/api/plant'

export default {
  // components: {
  //   TheDarkHeader
  // },
  data() {
    return {
      plantQuesId: 1,
      plantQues: {},
      statusStyle: { width: '0%' }
    }
  },
  created() {
    this.setData()
  },
  methods: {
    prev() {
      if (this.plantQuesId > 1) {
        this.plantQuesId -= 1
        this.setData()
      }
    },
    setChoice(choice) {
      this.$store.commit('setChoice', { num: this.plantQuesId, choice: choice })
      if (this.plantQuesId !== 11) {
        this.plantQuesId += 1
        this.setData()
      } else {
        this.$router.push({
          name: 'PlantTestResultView'
        })
      }
    },
    setData() {
      this.statusStyle.width = ((this.plantQuesId - 1) / 11) * 100 + '%'
      plantApi.getPlantQues(this.plantQuesId).then((res) => {
        this.plantQues = res.data
      })
    }
  }
}
</script>

<style scoped>
#backImg {
  background-image: url('@/assets/fern-1250903.jpg');
}

.btn-prev {
  margin-left: 10.5%;
  outline: 2px solid white;
  color: white;
  font-size: 25px;
}
.btn-prev:hover {
  background-color: black;
  color: white;
}

* {
  font-family: 'Cute Font', cursive;
}

.qBox {
  /* background-color: whitesmoke;*/
  text-align: center;
  border-radius: 20px;
  font-size: 33px;
  font-weight: 500;
  width: 80%;
  color: whitesmoke;
}

.answerList {
  background-color: whitesmoke;
  border-radius: 20px;
  display: block;
  width: 80%;
  border: 0px;
  font-size: 27px;
  color: #545454;
}

.answerList:hover,
.answerList:focus {
  background-color: #5f935d;
  color: whitesmoke;
}

.status {
  height: 20px;
  width: 80%;
  border-radius: 20px;
  background-color: white;
}

.statusBar {
  height: 100%;
  border-radius: 20px;
  /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#d1c6ab+0,c5b795+40,b1a075+100 */
  background: #d1c6ab;
  /* Old browsers */
  background: -moz-linear-gradient(top, #d1c6ab 0%, #c5b795 40%, #b1a075 100%);
  /* FF3.6-15 */
  background: -webkit-linear-gradient(
    top,
    #d1c6ab 0%,
    #c5b795 40%,
    #b1a075 100%
  );
  /* Chrome10-25,Safari5.1-6 */
  background: linear-gradient(to bottom, #d1c6ab 0%, #c5b795 40%, #b1a075 100%);
  /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#d1c6ab', endColorstr='#b1a075', GradientType=0);
}
</style>
