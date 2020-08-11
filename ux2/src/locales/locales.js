import Vue from 'vue'
import VueI18n from 'vue-i18n'
import en from './en'
import pl from './pl'

Vue.use(VueI18n)

export const localeStrings = [
  {
    code: 'en',
    value: 'English'
  }, {
    code: 'pl',
    value: 'Polski'
  }
]

Vue.config.lang = 'en' // TODO in future it is good place to global switch

const messages = {
  en: en,
  pl: pl
}

export const i18n = new VueI18n({
  locale: Vue.config.lang, messages
})
