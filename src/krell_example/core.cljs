(ns krell-example.core
  (:require [reagent.core :as r]
            [reagent.react-native :as rn]))

(def styles {:container
           {:flex 1
            :backgroundColor "#fff"
            :alignItems "center"
            :justifyContent "center"}

           :title
           {;; :fontWeight "bold"
            :fontSize 36
            :color "blue"}

           :body
           {:fontSize 24
            :color "blue"
            :textAlign "center"
            :margin 20}

           :list
           {:fontSize 24
            :color "white"
            :textAlign "center"}

           :listItem
           {:backgroundColor "rgba(1, 1, 255, 0.5)"
            :textAlign "center"
            :width 300
            :margin 5}

           :buttonContainer
           {:margin 10
            :flexDirection "row"
            :justifyContent "space-between"
            :alignItems "center"}

           :mybutton
           {:margin 10
            :padding 10}})

(defonce app-state (r/atom {:title "Shopping List"
                            :answer ""
                            :lists [{:title "Shopping" :list ["foo" "bar" "baz"]}
                                    {:title "Dinner" :list ["steak" "potatoes" "string beans"]}]
                            :testlist ["steak" "potatoes" "string beans"]}))

(defn input []
  (fn []
    (let [answer (:answer @app-state)]
      [rn/view {:style {:flex 1 :width "90%" :alignItems "center"}}
       [rn/text-input {:style {:backgroundColor "black" :color "white" :height 50 :width "90%"}
                         :default-value answer
                         :on-change-text #(swap! app-state assoc :answer %)}]])))

(defn root []
  [rn/keyboard-avoiding-view {:behavior "padding"
                               :style    {:flex            1
                                          :backgroundColor "#fff"
                                          :alignItems      "center"
                                          :justifyContent  "center"}}
   [rn/view {:style {:height 30}}]
   
   [rn/text {:style {:fontSize 36 :color "blue"}
             :on-press #(swap! app-state assoc-in [:answer] "Surprise!")}
    "Hello krell world!\n"]
   
   [input]

   ;; [show-list]
   ;; [rn/touchableopacity {:on-press #(swap! app-state assoc-in [:answer] "foobat")}
   ;;  [rn/image {:source splash-img :style {:width 150 :height 150}}]]

   [rn/text {:style (:body styles)}
    "\nShould this become a shopping list app?"]

   [rn/view {:style (:buttonContainer styles)}
    [rn/view {:style (:mybutton styles)}
     [rn/button {:title    "Yes"
                    :on-press #(swap! app-state assoc-in [:answer] "yes")}]]
    [rn/view {:style (:mybutton styles)}
     [rn/button {:title    "No"
                    :on-press #(swap! app-state assoc-in [:answer] "no")}]]]

   [rn/text {:style (:body styles)}
    "Answer: " (:answer @app-state)]

   [input]])

(defn hello []
  [rn/view {:style {:flex 1 :align-items "center" :justify-content "center"}}
   [rn/text {:style {:font-size 40}} "Hello krell world!"]])

(defn ^:export -main [& args]
  (r/as-element [root]))
