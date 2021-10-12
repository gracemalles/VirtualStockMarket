<template>
    <div class="loading-container">
        <div class="border">
            <div class="graph"></div>
            <div class="graph2"></div>
            <div class="graph3"></div>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.border{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  width: 150px;
  border: 3px solid var(--color-green);
  height: 150px;
  border-radius:  10px;
  background-color: transparent;
  padding: 20px;
  align-self: center;
  justify-self: center;
}

.graph{
  align-self: end;
  justify-self: center;
  height: 150px;
  background-color: var(--color-green);
  width: 20px;
  margin-bottom: 0;
  animation: width 2.3s infinite;
}
.graph2{
  align-self: end;
  justify-self: center;
  height: 150px;
  background-color: var(--color-green);
  width: 20px;
  margin-bottom: 0;
  animation: width 2.4s infinite;
}
.graph3{
  align-self: end;
  justify-self: center;
  height: 150px;
  background-color: var(--color-green);
  width: 20px;
  margin-bottom: 0;
  animation: width 2.5s infinite;
}

.loading-container {
    width: 100vw;
    height: 90vh;
    display: flex;
    align-content: center;
    justify-content: center;
    margin: 0;
    padding: 0;
}

@keyframes width{
  0%{
    height: 150px;
  }
  50%{
    height: 20px
  }
  100%{
    height:150px;
  }
}
</style>