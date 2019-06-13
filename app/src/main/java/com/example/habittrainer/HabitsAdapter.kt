package com.example.habittrainer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.single_card.view.*

// Note, this is the type of pattern you used to implement a RecyclerView
// 1. use RecyclerView.Adapter
// 2. Create the class HabitViewHolder (this would be any kind of holder you need for your specific impl)
class HabitsAdapter(val habits: List<Habit>) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {

    // This ViewHolder helps with performance by minimizing calls to get id, which can be expensive
    class HabitViewHolder(val card: View) : RecyclerView.ViewHolder(card)

    // This method defines the content of this card
    // From the pool of objects, when its bound to a specific Habit, or a specific item in general...
    // at that point the recycler view want to bind all the necessary items to the object to make is appear like the items have always been for the specific card
    // but what is actually happening is on the fly, the recycler view is gonna reuse some object that may have been used for say Habit1 before
    // but Habit1 is not on screen anymore, say its Habit10, so the recycler view is just going to reuse that Habit1 object
    // Specifies the contents for the shown item (habit)
    // Show the Habit at the position of index
    override fun onBindViewHolder(holder: HabitViewHolder, index: Int) {

        // This 'iv' that is being referenced here is the one defined in the HabitViewHolder class defined above that has the iv value passed in
        // In out example, this iv, or View would be a Card
        val habit = habits[index]
        holder.card.tv_title.text = habit.title
        holder.card.tv_description.text = habit.description
        holder.card.iv_icon.setImageResource(habit.image)
    }

    // This method will be called whenever the recycler view has to create a new object of this view holder type
    // Each of these view holders is going to contain the layout for the cards
    // Create a new ViewHolder
    // Create 1 item, which in our case means create 1 card
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HabitViewHolder {

        // We create some view that we have to pass in
        // This view comes from our card layout
        // When you have a layout and you want to use it, it is called "inflating" the layout
        // This line below, does a couple things:
        // 1. Inflate the layout from the parent, in this case "p0"
        // 2. Get the card that we want to use for the layout, "R.layout.single_card"
        // 3. As the second argument to inflate, reference the parent, "p0"
        val view = LayoutInflater.from(p0.context).inflate(R.layout.single_card, p0, false)

        return HabitViewHolder(view)
    }

    // This method returns the number of items we would like to show in our recycler view
    override fun getItemCount() = habits.size
}