package com.example.p6_flows.ui.fragment

import androidx.recyclerview.widget.ListAdapter

class MainFragmentAdapter : ListAdapter<Team, TeamListAdapter.ItemViewholder>(DiffCallback()) {
}