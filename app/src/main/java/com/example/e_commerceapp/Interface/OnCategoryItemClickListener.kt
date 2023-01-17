package com.example.e_commerceapp.Interface

import com.example.e_commerceapp.Domain.RecomendedDomain

interface OnCategoryItemClickListener {
    fun onItemClickListener(categoryItems: ArrayList<RecomendedDomain>)
}