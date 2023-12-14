<script setup>
// IMPORT dependency
import { defineEmits, ref } from 'vue'
import { defineProps } from 'vue';
import axios from 'axios';

// DATA
const search = ref('');
const filteredPizzas = ref();

// EMITS
const emits = defineEmits(["deleted"]);

// PROPS
const props = defineProps({
    pizzas: {
        type: Array,
        required: true
    }
});

// Functions
const filterPizzas = () => {
    search.value = search.value.toLowerCase();
    filteredPizzas.value = props.pizzas.filter(pizza => pizza.name.toLowerCase().includes(search.value));
}

const deletePizza = async (id) => {
    const data = await axios.delete(
        `http://localhost:8080/api/pizzas/${id}`
    );

    emits("deleted");

    return
}
</script>

<template>
    <h1>Le mie pizze</h1>
    <label for="search">Cerca la tua pizza per nome </label>
    <input @keyup="filterPizzas" type="search" id="search" v-model="search">

    <ul>
        <li v-if="!filteredPizzas" v-for="pizza in pizzas" :key="pizza.id">
            {{ pizza.name + " " }} {{ pizza.price + " euro" }}
            <button type="button" @click="deletePizza(pizza.id)">Cancella</button>
        </li>
        <li v-else v-for="pizza in filteredPizzas">
            {{ pizza.name + " " }} {{ pizza.price + " euro" }}
            <button type="button" @click="deletePizza(pizza.id)">Cancella</button>
        </li>
    </ul>
</template>


<style>
ul li {
    list-style-type: none;
}

button {
    background-color: red;
    color: black;
    margin-top: 10px;
    margin-bottom: 10px;
}
</style>