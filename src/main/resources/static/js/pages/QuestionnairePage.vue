<template>
    <div>
        <navbar :full-navbar="false"></navbar>
        <div class="d-flex justify-content-center">
            <div class="card" style="width: 23rem;">
                <div class="card-body">
                    <div v-for="field in fields">

                        <div v-if="field.field.type === 'SINGLE_LINE_TEXT'" class="mt-3">
                            <div class="form-group mt-2">
                                <label>{{field.field.label}}</label>
                                <input type="text" class="form-control" v-model="field.response">
                            </div>
                        </div>

                        <div v-if="field.field.type === 'MULTILINE_TEXT'" class="mt-3">
                            <div class="form-group mt-2">
                                <label>{{field.field.label}}</label>
                                <textarea type="text" class="form-control" rows="3" v-model="field.response"></textarea>
                            </div>
                        </div>

                        <div v-if="field.field.type === 'RADIO_BUTTON'" class="mt-3">
                            <div class="form-group mt-2">
                                <label>{{field.field.label}}</label>
                                <div v-for="option in field.field.options">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="exampleRadios" :id="option" :value="option" v-model="field.response">
                                        <label class="form-check-label" :for="option">
                                            {{option}}
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div v-if="field.field.type === 'CHECKBOX'" class="mt-3">
                            <div class="form-group mt-1">
                                <label>{{field.field.label}}</label>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" :id="field.field.label" v-model="field.response">
                                    <label class="form-check-label" :for="field.field.label">
                                        {{field.field.options[0]}}
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div v-if="field.field.type === 'COMBOBOX'" class="mt-3">
                            <div class="form-group mt-2">
                                <label>{{field.field.label}}</label>
                                <select class="custom-select" v-model="field.response">
                                    <option v-for="option in field.field.options" v-bind:value="option">{{option}}</option>
                                </select>
                            </div>
                        </div>

                        <div v-if="field.field.type === 'DATE'" class="mt-3">
                            <div class="form-group mt-2">
                                <label>{{field.field.label}}</label>
                                <input type="date" class="form-control" v-model="field.response">
                            </div>
                        </div>

                    </div>

                    <div class="d-flex input-group mt-4">
                        <button type="submit" class="flex-fill btn btn-primary" @click="saveResponse">CHANGE</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Navbar from 'components/navbar/Navbar.vue'
    import { sendResponse } from 'util/websocket'

    export default {
        name: "QuestionnairePage",
        components: {
            Navbar
        },
        data() {
            return {
                fields: []
            }
        },
        created() {
            this.$resource('/questionnaire').get().then(result => {
                if(result.ok) {
                    result.data.forEach(element => {
                        let elem = {field: element, response: null}
                        this.fields.push(elem)
                    })
                }
            })
        },
        methods: {
            saveResponse() {
                let response = []
                this.fields.forEach(element => {
                    let responseValue = element.field.type === 'CHECKBOX' ? (element.response ? 'True' : 'False') : element.response
                    let fieldResponse = {fieldId: element.field.id, fieldResponse: responseValue}
                    response.push(fieldResponse)
                })

                //this.$resource('/questionnaire').save({response: response}).then()
                sendResponse({response: response})
            }
        }
    }
</script>

<style scoped>

</style>