object Versions {
    const val compose = "1.0.0-beta09"
    const val kotlin = "1.5.10"
    const val retrofit = "2.9.0"

    /**
     * Hilt 2.31 이후 부터 뷰모델 주입 방식 변경됨
     * 기존 : class StartViewModel @ViewModelInject constructor(
     *          @Assisted private val savedStateHandle: SavedStateHandle,
     * 필요 : implementation 'androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02'
     *       kapt 'androidx.hilt:hilt-compiler:1.0.0-alpha02'
     *
     * 변경 : @HiltViewModel
     *       class StartViewModel @Inject constructor(
     *          private val savedStateHandle: SavedStateHandle,
     * 필요 : kapt 'androidx.hilt:hilt-compiler:1.0.0-alpha02'
     */
    const val hilt = "2.37"

    /**
     * Room : https://developer.android.com/training/data-storage/room/accessing-data?hl=ko
     */
    const val room = "2.3.0"
}