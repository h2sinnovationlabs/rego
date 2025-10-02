package com.example.nativeandroidbasearchitecture.screens.raiserequest

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nativeandroidbasearchitecture.R
import com.example.nativeandroidbasearchitecture.screens.components.DropdownField
import com.example.nativeandroidbasearchitecture.screens.components.RegoButton
import com.example.nativeandroidbasearchitecture.screens.components.TransparentInputField
import com.example.nativeandroidbasearchitecture.ui.theme.Color00954D
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_60
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_87
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_90
import com.example.nativeandroidbasearchitecture.ui.theme.ColorFBFBFB
import com.example.nativeandroidbasearchitecture.ui.theme.NativeAndroidBaseArchitectureTheme
import com.example.nativeandroidbasearchitecture.ui.theme.fontMediumMontserrat

data class PartType(
    val id: String,
    val title: String,
    val icon: Int
)

@Composable
fun RaiseRequestScreen(
    onBack: () -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onBack() }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Raise a Request",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color1A1A1A_90()
                )
            }
        }
    ) { paddingValues ->
        RaiseRequestScreenContent(
            onSubmit = onSubmit,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun RaiseRequestScreenContent(
    modifier: Modifier = Modifier,
    onSubmit: () -> Unit = {},
) {
    val context = LocalContext.current

    // State variables for all fields
    var carMakeExpanded by remember { mutableStateOf(false) }
    var carModelExpanded by remember { mutableStateOf(false) }
    var fuelTypeExpanded by remember { mutableStateOf(false) }
    var carVariantExpanded by remember { mutableStateOf(false) }
    var dealerLocationExpanded by remember { mutableStateOf(false) }
    var policyTypeExpanded by remember { mutableStateOf(false) }

    var selectedCarMake by remember { mutableStateOf("") }
    var selectedCarModel by remember { mutableStateOf("") }
    var selectedFuelType by remember { mutableStateOf("") }
    var selectedCarVariant by remember { mutableStateOf("") }
    var selectedDealerLocation by remember { mutableStateOf("") }
    var selectedPolicyType by remember { mutableStateOf("") }

    var dealerName by remember { mutableStateOf("") }
    var advisorName by remember { mutableStateOf("") }
    var advisorContactNumber by remember { mutableStateOf("") }
    var claimNumber by remember { mutableStateOf("") }
    var carRegNumber by remember { mutableStateOf("") }
    var makeYear by remember { mutableStateOf("") }

    var isInventoryPickup by remember { mutableStateOf(false) }
    var selectedPartType by remember { mutableStateOf("alloy_wheels") }

    // Image picker states
    val selectedImages = remember { mutableStateListOf<Uri>() }
    var showImagePickerDialog by remember { mutableStateOf(false) }
    var hasPermissions by remember { mutableStateOf(false) }
    var tempImageUri: Uri? by remember { mutableStateOf(null) }

    // Image picker launchers
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            println("Gallery image selected: $it")
            selectedImages.add(it)
        } ?: println("Gallery selection cancelled")
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        println("Camera capture result: $success")
        if (success) {
            tempImageUri?.let { uri ->
                println("Adding camera image: $uri")
                selectedImages.add(uri)
            } ?: println("Temp image URI is null")
        }
    }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        println("Permissions result: $permissions")
        hasPermissions = permissions[Manifest.permission.CAMERA] == true
        println("Camera permission granted: $hasPermissions")
    }

    // Check permissions on launch
    LaunchedEffect(Unit) {
        val permissions =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_MEDIA_IMAGES
                )
            } else {
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        permissionLauncher.launch(permissions)
    }

    // Helper function to create temp file for camera
    fun createTempImageUri(context: Context): Uri {
        val imageFileName = "temp_image_${System.currentTimeMillis()}.jpg"
        val storageDir = context.getExternalFilesDir("Pictures") ?: context.filesDir
        val tempFile = java.io.File(storageDir, imageFileName)

        return androidx.core.content.FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            tempFile
        )
    }

    // Hard coded data
    val carMakes = listOf("Toyota", "Honda", "Maruti Suzuki", "Hyundai", "Mahindra", "Tata")
    val carModels = listOf("Camry", "Civic", "Swift", "i20", "XUV700", "Nexon")
    val fuelTypes = listOf("Petrol", "Diesel", "CNG", "Electric", "Hybrid")
    val carVariants = listOf("Base", "Mid", "Top", "VXi", "ZXi", "Alpha")
    val dealerLocations = listOf("Mumbai", "Delhi", "Bangalore", "Chennai", "Kolkata", "Pune")
    val policyTypes =
        listOf("Comprehensive", "Third Party", "Zero Depreciation", "Return to Invoice")

    // Part types list
    val partTypes = listOf(
        PartType(
            id = "alloy_wheels",
            title = "Alloy wheels",
            icon = R.drawable.alloy_wheel
        ),
        PartType(
            id = "headlamps",
            title = "Headlamps",
            icon = R.drawable.car_light
        ),
        PartType(
            id = "plastic_repair",
            title = "Plastic repair",
            icon = R.drawable.car_seat
        )
    )

    @Composable
    fun LabelWithAsterisk(text: String) {
        val isRequired = text.endsWith("*")
        if (!isRequired) {
            Text(
                text = text,
                style = TextStyle(
                    color = Color1A1A1A_60(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        } else {
            val label = text.removeSuffix("*")
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color1A1A1A_90(), fontSize = 12.sp)) {
                        append(label)
                    }
                    withStyle(SpanStyle(color = Color(0xFFE7503D))) {
                        append("*")
                    }
                }
            )
        }
    }

    // Image picker dialog
    if (showImagePickerDialog) {
        AlertDialog(
            onDismissRequest = { showImagePickerDialog = false },
            title = { Text("Select Image") },
            text = { Text("Choose an option to add a photo") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showImagePickerDialog = false
                        if (hasPermissions) {
                            try {
                                tempImageUri = createTempImageUri(context)
                                println("Created temp URI: $tempImageUri")
                                tempImageUri?.let { uri ->
                                    cameraLauncher.launch(uri)
                                } ?: println("Failed to create temp URI")
                            } catch (e: Exception) {
                                println("Error creating temp file: ${e.message}")
                                e.printStackTrace()
                            }
                        } else {
                            println("Camera permission not granted, requesting permission")
                            permissionLauncher.launch(
                                arrayOf(Manifest.permission.CAMERA)
                            )
                        }
                    }
                ) {
                    Text("Camera")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        galleryLauncher.launch("image/*")
                        showImagePickerDialog = false
                    }
                ) {
                    Text("Gallery")
                }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        // Part type section
        Text(
            text = "Part type",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color1A1A1A_90()
        )
        Text(
            text = "Choose part for repair",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color1A1A1A_60(),
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Part selection - Lazy Row with part types list
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(partTypes) { partType ->
                PartTypeCard(
                    icon = partType.icon,
                    title = partType.title,
                    isSelected = selectedPartType == partType.id,
                    onClick = { selectedPartType = partType.id }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Part photos section
        Text(
            text = "Part photos",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color1A1A1A_90()
        )
        Text(
            text = "Upload photos of the part",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color1A1A1A_60(),
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Photos row with Add Photo and selected photos
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Selected photos
            items(selectedImages) { imageUri ->
                Box {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = "Selected Photo",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.camera),
                        error = painterResource(id = R.drawable.camera),
                        fallback = painterResource(id = R.drawable.camera)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "Delete Photo",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(24.dp)
                            .padding(end = 5.dp, top = 5.dp)
                            .clickable {
                                selectedImages.remove(imageUri)
                            }
                    )
                }
            }

            // Add Photo button
            item {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF4CAF50),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            showImagePickerDialog = true
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = "Add Photo",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp),
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Add Photo",
                            fontSize = 10.sp,
                            color = Color(0xFF4CAF50)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Other Details section
        Text(
            text = "Other Details",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color1A1A1A_90(),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Fill other details",
            fontSize = 12.sp,
            color = Color1A1A1A_60(),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Car Make Dropdown
        DropdownField(
            label = "",
            value = selectedCarMake,
            onValueChange = { selectedCarMake = it },
            onDropdownExpand = { carMakeExpanded = true },
            expanded = carMakeExpanded,
            options = carMakes,
            placeholder = "Select Car Make",
            onDismissRequest = { carMakeExpanded = false },
            labelComposable = { LabelWithAsterisk("Car Make*") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Car Model Dropdown
        DropdownField(
            label = "",
            value = selectedCarModel,
            onValueChange = { selectedCarModel = it },
            onDropdownExpand = { carModelExpanded = true },
            expanded = carModelExpanded,
            options = carModels,
            placeholder = "Select Car Model",
            onDismissRequest = { carModelExpanded = false },
            labelComposable = { LabelWithAsterisk("Car Model*") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fuel Type Dropdown
        DropdownField(
            label = "",
            value = selectedFuelType,
            onValueChange = { selectedFuelType = it },
            onDropdownExpand = { fuelTypeExpanded = true },
            expanded = fuelTypeExpanded,
            options = fuelTypes,
            placeholder = "Select Fuel Type",
            onDismissRequest = { fuelTypeExpanded = false },
            labelComposable = { LabelWithAsterisk("Fuel Type*") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Car Variant Dropdown
        DropdownField(
            label = "",
            value = selectedCarVariant,
            onValueChange = { selectedCarVariant = it },
            onDropdownExpand = { carVariantExpanded = true },
            expanded = carVariantExpanded,
            options = carVariants,
            placeholder = "Select Car Variant",
            onDismissRequest = { carVariantExpanded = false },
            labelComposable = { LabelWithAsterisk("Car Variant*") }
        )
        // Inventory Pickup Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = isInventoryPickup,
                onCheckedChange = { isInventoryPickup = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color00954D,
                    uncheckedColor = Color00954D,
                    checkmarkColor = Color.White
                )
            )
            Text(
                text = "Is this an Inventory Pick up?",
                style = fontMediumMontserrat().copy(fontSize = 12.sp, color = Color1A1A1A_90())
            )
        }

        // Dealer Name Input
        TransparentInputField(
            label = "",
            value = dealerName,
            onValueChange = { dealerName = it },
            placeholder = "Enter Dealer Name",
            labelComposable = { LabelWithAsterisk("Dealer Name*") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dealer Location Dropdown
        DropdownField(
            label = "",
            value = selectedDealerLocation,
            onValueChange = { selectedDealerLocation = it },
            onDropdownExpand = { dealerLocationExpanded = true },
            expanded = dealerLocationExpanded,
            options = dealerLocations,
            placeholder = "Select Dealer Location",
            onDismissRequest = { dealerLocationExpanded = false },
            labelComposable = { LabelWithAsterisk("Dealer Location*") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Advisor Name Input
        TransparentInputField(
            label = "",
            value = advisorName,
            onValueChange = { advisorName = it },
            placeholder = "Enter Advisor Name",
            labelComposable = { LabelWithAsterisk("Advisor Name*") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Advisor Contact Number Input
        TransparentInputField(
            label = "",
            value = advisorContactNumber,
            onValueChange = { advisorContactNumber = it },
            placeholder = "Enter Advisor Contact Number",
            keyboardType = KeyboardType.Phone,
            labelComposable = { LabelWithAsterisk("Advisor Contact Number*") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Policy Type Dropdown
        DropdownField(
            label = "",
            value = selectedPolicyType,
            onValueChange = { selectedPolicyType = it },
            onDropdownExpand = { policyTypeExpanded = true },
            expanded = policyTypeExpanded,
            options = policyTypes,
            placeholder = "Select Policy Type",
            onDismissRequest = { policyTypeExpanded = false },
            labelComposable = { LabelWithAsterisk("Policy Type*") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Claim Number Input
        TransparentInputField(
            label = "Claim Number",
            value = claimNumber,
            onValueChange = { claimNumber = it },
            placeholder = "Enter Claim Number"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Car Reg Number and Make Year Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TransparentInputField(
                modifier = Modifier.weight(1f),
                label = "Car Reg Number",
                value = carRegNumber,
                onValueChange = { carRegNumber = it },
                placeholder = "Registration Number"
            )

            TransparentInputField(
                modifier = Modifier.weight(1f),
                label = "Make Year",
                value = makeYear,
                onValueChange = { makeYear = it },
                placeholder = "Enter Make year",
                keyboardType = KeyboardType.Number
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Submit Button
        RegoButton(
            onClick = { onSubmit() },
            text = "Submit"
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PartTypeCard(
    icon: Int,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(120.dp, 100.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isSelected) 1.dp else 1.dp,
                color = if (isSelected) Color(0xFF4CAF50) else Color(0xFFE0E0E0),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(26.dp)
                    .height(24.dp)
                    .align(Alignment.TopEnd)
                    .background(
                        color = Color00954D,
                        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 8.dp)
                    ),
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.tick),
                    contentDescription = title,
                    tint = Color.White
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(32.dp)
                .align(Alignment.TopCenter)
                .background(color = Color.Black.copy(alpha = 0.06f), shape = CircleShape),
        ) {
            Icon(
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = if (isSelected) Color(0xFF4CAF50) else Color1A1A1A_60()
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(34.dp)
                .fillMaxWidth()
                .background(
                    if (isSelected) Color(0xFFEFFDEF) else ColorFBFBFB,
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                )
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color1A1A1A_87()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RaiseRequestScreenPreview() {
    NativeAndroidBaseArchitectureTheme {
        RaiseRequestScreen()
    }
}