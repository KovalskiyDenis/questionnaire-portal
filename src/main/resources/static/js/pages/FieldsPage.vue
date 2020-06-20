<template>
    <div>
        <navbar full-navbar="true"></navbar>
        <button class="btn" @click="dosmth">Click</button>
    </div>
</template>

<script>
    import Navbar from 'components/navbar/Navbar.vue'

    export default {
        components: {
            Navbar
        },
        name: "FieldsPage",
        methods: {
            dosmth() {
                const accessToken = 'Bearer_' + localStorage.getItem("token")
                fetch('/fields', {method:'post', headers: {'Content-Type': 'application/json', 'Authorization': accessToken}, body: JSON.stringify({data: 'qwer'})}).then(console.log)
            }
        }
    }
</script>

<style scoped>

</style>