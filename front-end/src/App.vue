<!-- SCRIPT -->
<script setup>
// Import dependency
import { onMounted, ref } from 'vue';
import axios from 'axios';

// Import components
import PizzaIndex from './components/PizzaIndex.vue';
import PizzaForm from './components/PizzaForm.vue';

// Data
const pizzas = ref(null);
const isCreatingPizza = ref(false);

// Functions
const createdPizza = () => {
  isCreatingPizza.value = false;
  console.log(isCreatingPizza.value);
  getPizzas();
}


// Get all pizzas with axios
const getPizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/pizzas");
  pizzas.value = data.data
}



// Mounted
onMounted(getPizzas);

</script>

<!-- TEMPLATE -->
<template>
  <PizzaIndex v-if="!isCreatingPizza" :pizzas="pizzas" @deleted="getPizzas" />
  <PizzaForm @created="createdPizza" v-if="isCreatingPizza" @back="isCreatingPizza = false" />
  <button v-if="!isCreatingPizza" @click="isCreatingPizza = true">
    Crea una nuova pizza</button>
</template>


<!-- STYLE -->
<style scoped>
button {
  background-color: black;
  color: white;
}
</style>
