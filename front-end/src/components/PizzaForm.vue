<script setup>
// IMPORT dependency
import { defineEmits, ref } from 'vue'
import axios from 'axios';

// DATA
const newPizza = ref({
    name: null,
    description: null,
    price: null
});

// EMITS
const emits = defineEmits(["back", "created"]);

// Function
const submit = async () => {
    const data = await axios.post(
        "http://localhost:8080/api/pizzas",
        newPizza.value
    );

    emits("created");

    return
}

</script>

<template>
    <h1>Inserisci una nuova pizza</h1>
    <form @submit.prevent="submit">
        <label for="name">Nome pizza </label>
        <input id="name" type="text" v-model="newPizza.name">
        <br>
        <label for="description">Descrizione pizza </label>
        <input id="description" type="text" v-model="newPizza.description">
        <br>
        <label for="price">Prezzo pizza </label>
        <input id="price" type="number" v-model="newPizza.price">
        <br>
        <button type="button" @click="$emit('back')">Torna alle pizze</button>
        <button type="submit">Crea</button>
    </form>
</template>

<style scoped>
button {
    background-color: black;
    color: white;
    margin-top: 10px;
    margin-right: 10px;
}
</style>