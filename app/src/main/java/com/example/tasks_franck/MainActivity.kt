package com.example.tasks_franck

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasks_franck.adapters.MyTasksAdapter
import com.example.tasks_franck.databinding.ActivityMainBinding
import com.example.tasks_franck.models.Tasks
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var tasksList: MutableList<Tasks> = mutableListOf(
        Tasks("Watch movie", false),
        Tasks("Buy groceries", true),
        Tasks("Finish cleaning bathroom", false),
        Tasks("See doctor", false),
        Tasks("Do homework", false)
    )
    lateinit var taskAdapter: MyTasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        taskAdapter = MyTasksAdapter(tasksList)
        binding.taskRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.taskRecyclerView.adapter = taskAdapter
        binding.taskRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.btnAddTask.setOnClickListener {
            addTaskName()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.deleteTasks -> {
                deleteAllTasks()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addTaskName() {
        val nameFromUI: String = binding.etName.text.toString()
        val isPriorityFromUI: Boolean = binding.mySwitch.isChecked

        val taskToAdd: Tasks = Tasks(nameFromUI, isPriorityFromUI)
        tasksList.add(taskToAdd)
        taskAdapter.notifyDataSetChanged()

        val snackbar = Snackbar.make(binding.root, "Adding ${taskToAdd.toString()}", Snackbar.LENGTH_LONG)
        snackbar.show()

        binding.etName.setText("")
        binding.mySwitch.isChecked = false
    }

    private fun deleteAllTasks() {
        tasksList.clear()
        taskAdapter.notifyDataSetChanged()

        val snackbar = Snackbar.make(binding.root, "All tasks deleted!", Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}
